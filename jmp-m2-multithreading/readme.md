Task 1 (Optional) - Factorial via FJP

Cost: 1 point.

Use FJP to calculate factorial. Compare with the sequential implementation. Use BigInteger to keep values.

Task 2 - Multithreading Sorting via FJP

Cost: 1 points.

Implement Merge Sort or Quick Sort algorithm that sorts huge array of integers in parallel using Fork/Join framework.

Task 3 - File Scanner via FJP

Cost: 1 points.

Create CLI application that scans a specified folder and provides detailed statistics:

File count.
Folder count.
Size (sum of all files size) (similar like Windows context menu Properties). Since the folder may contain huge number of files the scanning process should be executed in a separate thread displaying an informational message with some simple animation like progress bar in CLI (up to you, but I'd like to see that task is in progress).
Once task is done, the statistics should be displayed in the output immediately. Additionally, there should be ability to interrupt the process pressing some reserved key (for instance c). Of course, use Fork-Join Framework for implementation parallel scanning.

Task 4 - Completable Future Helps to Build Open Salary Society

Cost: 1 points.

Assume, we have REST endpoint that returns a list of hired Employees.

REST endpoint is wrapped by Java service class that consuming this endpoint.
Fetch a list of Employee objects asynchronously by calling the hiredEmployees().
Join another CompletionStage<List> that takes care of filling the salary of each hired employee, by calling the getSalary(hiredEmployeeId) method which returns a CompletionStage that asynchronously fetches the salary (again could be consuming a REST endpoint).
When all Employee objects are filled with their salaries, we end up with a List<CompletionStage>, so we call <special operation on CF> to get a final stage that completes upon completion of all these stages.
Print hired Employees with their salaries via <special operation on CF> on final stage.
Provide correct solution with CF usage and use appropriate CF operators instead <special operation on CF>. Why does the CF usage improve performance here in comparison with synchronous approach? Discuss it with mentor. How thread waiting is implemented in synchronous world?

Task 5 (prodcons module):

Solve producer–consumer problem

Cost: 1 points.

Using:

Semaphore
BlockingQueue
Task 6

Cost: 1 points.

RecursiveTask

Give example from RecursiveTask javadoc.
Write FibonacciTask that implements RecursiveTask.
Apply suggestion from javadoc to check minimum granularity size less or equal 10. And in that case use linear algorithm.
Using unit test check that your code works correctly:
assertEquals(1134903170L, new ForkJoinPool().invoke(new FibonacciTask(45)).longValue());

RecursiveAction

Sum of double squares

Give last example from RecursiveAction javadoc about calculation of sum of squares in double[] array.
Use double array of half-billion size 500_000_000 filled by random doubles.
Compare speed with direct linear calculation (you may use Stream API as well):
double sum = 0; for (double v : ARRAY) { sum += v * v; }

Task 7

Cost: 1 points.

Blurring for Clarity (forkjoin module)

Execute ForkBlur example from Java SE Fork/Join tutorial

Task 8 (Optional) - PC Assembly Line with Akka

Cost: 3 points + 1 points for bonus tasks.

Automatize PC assembly line with Akka Framework (use Java API).

Requirements:

Create PC object and fill it with data from operation to operation.
Make more than 2 levels-hierarchy of actors.
Log all steps and meta info about Self and Sender with AkkaLogger.
Create 1 actor (or more) for each step of process and 1 router to route all requests for assembling.
Write Service layer with CompletableFutures in signatures (handle router answers or inbox events) where it is possible.
Provide client code to call Service layer methods (your choice).
Try with different dispatchers and pick one for your solution.
Tune ForkJoin executor for your environment (change configuration).
The choice should be based on performance tests *
Throughput of assembly line should be more than 100 000 assembled PCs per second *
Throughput of assembly line should be more than 1 000 000 assembled PCs per second *
* - optional points (5 points per subtask).

An example of actions is described in this paper.
