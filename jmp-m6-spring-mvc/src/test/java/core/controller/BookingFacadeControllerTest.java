package core.controller;

import static org.junit.Assert.assertEquals;

import core.config.TestConfig;
import core.model.Event;
import core.model.Ticket;
import core.model.User;
import core.model.impl.EventImpl;
import core.model.impl.TicketImpl;
import core.model.impl.UserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * BookingFacadeControllerTest
 * Date: 03/06/2023
 *
 * @author Yauheni Antsipenka
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
public class BookingFacadeControllerTest {

    public static final Event FIRST_EVENT = new EventImpl(1, "firstTitle", LocalDate.of(1990, 12, 12));
    public static final Ticket FIRST_TICKET = new TicketImpl(1, 1, 1, Ticket.Category.PREMIUM, 1);
    public static final Ticket SECOND_TICKET = new TicketImpl(2, 2, 2, Ticket.Category.BAR, 2);
    public static final User FIRST_USER = new UserImpl(1, "name1", "email1");
    public static final User SECOND_USER = new UserImpl(2, "name2", "email2");

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEventById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/booking/event/" + FIRST_EVENT.getId()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        assertEquals("event/event.html", Objects.requireNonNull(mvcResult.getModelAndView()).getViewName());
        assertEquals(FIRST_EVENT, Objects.requireNonNull(mvcResult.getModelAndView()).getModel().get("event"));
    }

    @Test
    public void testGetEventByIdWhenNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/booking/event/" + 123))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        assertEquals("event/notFound.html", Objects.requireNonNull(mvcResult.getModelAndView()).getViewName());
    }

    @Test
    public void testGetEventByTitle() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/booking/event/title/" +
                FIRST_EVENT.getTitle()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        assertEquals("event/events.html", Objects.requireNonNull(mvcResult.getModelAndView()).getViewName());
        assertEquals(List.of(FIRST_EVENT),
            Objects.requireNonNull(mvcResult.getModelAndView()).getModel().get("events"));
    }

    @Test
    public void testGetEventByTitleWhenNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/booking/event/title/not_found_title"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        assertEquals("event/notFound.html", Objects.requireNonNull(mvcResult.getModelAndView()).getViewName());
    }

    @Test
    public void testGetEventForDay() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/booking/event/day/" +
                FIRST_EVENT.getDate()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        assertEquals("event/events.html", Objects.requireNonNull(mvcResult.getModelAndView()).getViewName());
        assertEquals(List.of(FIRST_EVENT),
            Objects.requireNonNull(mvcResult.getModelAndView()).getModel().get("events"));
    }

    @Test
    public void testGetEventForDayWhenNotFound() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/booking/event/day/" +
                LocalDate.of(1111, 1, 1)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        assertEquals("event/notFound.html", Objects.requireNonNull(mvcResult.getModelAndView()).getViewName());
    }

    @Test
    public void testCreateNewEvent() throws Exception {
        String eventIdToCreate = "4";
        String eventToCreate = "{\"id\": " + eventIdToCreate + ", \"title\": \"fourthTitle\", \"date\": \"1998-02-02\"}";
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/booking/event/create")
                    .content(eventToCreate)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals("Event with id " + eventIdToCreate + " was created", response);
    }

    @Test
    public void testUpdateEvent() throws Exception {
        String eventIdToUpdate = "3";
        String eventToUpdate = "{\"id\": " + eventIdToUpdate + ", \"title\": \"newTitle\", \"date\": \"1991-12-12\"}";
        String response = mockMvc.perform(MockMvcRequestBuilders.put("/booking/event/update/" + eventIdToUpdate)
                    .content(eventToUpdate)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals("Event with id " + eventIdToUpdate + " was updated", response);
    }
}
