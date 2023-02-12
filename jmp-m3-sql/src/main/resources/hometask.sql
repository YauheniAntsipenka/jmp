--1. Select all primary skills that contain more than one word (please note that both ‘-‘ and ‘ ’ could be used as a separator)
select distinct unnest(string_to_array(temp_arr, ' '))
from
	(select unnest(string_to_array(primary_skill, '-')) as temp_arr
	from student) as temp_table

--2. Select all students who does not have second name (it is absent or consists from only one letter/letter with dot)
select *
from  student s
where s.surname is null
	or length(s.surname) = 1
	or s.surname ~ '^[a-zA-Z].$'

--3. Select number of students passed exams for each subject and order result by number of student descending
select er.subject_id, count(er.student_id)
from exam_result er
where er.mark >= 4
group by er.subject_id
order by count(er.student_id) desc

--4. Select number of students with the same exam marks for each subject.
select er.subject_id , er.mark, count(er.student_id)
from exam_result er
group by er.subject_id, er.mark

--5. Select students who passed at least two exams for different subject.
select er.student_id
from exam_result er
where er.mark >= 4
group by er.student_id
having count(distinct er.subject_id) >= 2

--6. Select students who passed at least two exams for the same subject
select er.student_id
from exam_result er
where er.mark >= 4
group by er.subject_id, er.student_id
having count(er.subject_id) >= 2

--7. Select all subjects which exams passed only students with the same primary skills
with temp_table as (select array_agg(s.id) as ids
	from student s
	group by s.primary_skill
	having count(*) > 1)
select er.subject_id
from exam_result er
where er.mark >= 4
	and er.student_id in (select unnest(ids) from temp_table)

--8. Select all subjects which exams passed only students with the different primary skills. It means that all students passed the exam for the one subject must have different primary skill
with temp_table1 as (select er.subject_id as subject_id, array_agg(distinct s.primary_skill) as primary_skills
	from exam_result er
	join student s on s.id = er.student_id
	where er.mark >= 4
	group by er.subject_id),
temp_table2 as (select er.subject_id as subject_id, array_agg(s.primary_skill) as primary_skills
	from exam_result er
	join student s on s.id = er.student_id
	where er.mark >= 4
	group by er.subject_id)
select s.name
from temp_table1
join temp_table2 on temp_table1.subject_id = temp_table2.subject_id
join subject s on s.id = temp_table1.subject_id
where array_length(temp_table1.primary_skills, 1) = array_length(temp_table2.primary_skills, 1)

--9. Select students who does not pass any exam using each the following operator: – 0.5 points.
--Outer join
with temp_table as (
	select er.student_id, array_agg(mark) as marks
	from exam_result er
	join student s on s.id = er.student_id
	group by er.student_id)
select student_id
from temp_table
where marks <@ array[1,2,3,4]::smallint[]
--Subquery with ‘not in’ clause
select *
from student s
where s.id not in (select er.student_id from exam_result er where er.mark >= 4)
-- Subquery with ‘any‘ clause
select *
from student s
where s.id != any(select er.student_id from exam_result er where er.mark >= 4) is true

-- 10. Select all students whose average mark is bigger than overall average mark.
select er.student_id
from exam_result er
group by er.student_id
having avg(er.mark) > (select avg(er.mark) from exam_result er)

-- 11. Select top 5 students who passed their last exam better than average students.
select er.student_id
from exam_result er
where er.mark > (select avg(er.mark) from exam_result er)
order by er.mark desc
limit 5

-- 12. Select biggest mark for each student and add text description for the mark
-- In case if student has not passed any exam ‘not passed' should be returned.
-- If student mark is 1,2,3 – it should be returned as ‘BAD’
-- If student mark is 4,5,6 – it should be returned as ‘AVERAGE’
-- If student mark is 7,8 – it should be returned as ‘GOOD’
-- If student mark is 9,10 – it should be returned as ‘EXCELLENT’
select er.student_id, max(er.mark),
	case
		when max(er.mark) in (1,2,3) then 'BAD'
		when max(er.mark) in (4,5,6) then 'AVERAGE'
		when max(er.mark) in (7,8) then 'GOOD'
		when max(er.mark) in (9,10) then 'EXCELLENT'
		else 'not passed'
	end
from exam_result er
group by er.student_id

-- 13. Select number of all marks for each mark type (‘BAD’, ‘AVERAGE’,…)
select count(*), 'BAD' as text_descr from exam_result er where er.mark in (1,2,3)
union
select count(*), 'AVERAGE' as text_descr from exam_result er where er.mark in (4,5,6)
union
select count(*), 'GOOD' as text_descr from exam_result er where er.mark in (7,8)
union
select count(*), 'EXCELLENT' as text_descr from exam_result er where er.mark in (9,10)
