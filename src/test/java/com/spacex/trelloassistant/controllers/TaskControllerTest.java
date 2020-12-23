package com.spacex.trelloassistant.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spacex.trelloassistant.entity.CategoryEntity;
import com.spacex.trelloassistant.entity.TaskEntity;
import com.spacex.trelloassistant.entity.TypeTaskEntity;
import com.spacex.trelloassistant.services.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskServiceImpl taskService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<TaskEntity> taskList;

    @BeforeEach
    void setUp() {
        this.taskList = new ArrayList<>();
        this.taskList.add(new TaskEntity((long)1,"D1","t1",new CategoryEntity((long) 1,"C1"),new TypeTaskEntity((long)1,"T1","Tag",true,false,true)));
    }
    @Test
    void listAll() throws Exception {
        given(taskService.findAll()).willReturn(this.taskList);

        this.mockMvc.perform(get("/v1/Tasks/listAll"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()", is(taskList.size())));
    }
    @Test
    void saveTask() {
    }

    @Test
    void findIdTask() throws Exception {
        Long idTask = 1L;
        TaskEntity task = new TaskEntity(idTask,"D1","t1",new CategoryEntity( 1L,"C1"),new TypeTaskEntity(1L,"T1","Tag",true,false,true));
        given(taskService.findBy(idTask)).willReturn(Optional.of(task));

        this.mockMvc.perform(get("/v1/Tasks/{id}",idTask))
                .andExpect(status().isOk());
    }

    @Test
    void editTask() {
    }

    @Test
    void deleteTask() throws Exception{
        Long idTask = 1L;
        TaskEntity task = new TaskEntity(idTask,"D1","t1",new CategoryEntity( 1L,"C1"),new TypeTaskEntity(1L,"T1","Tag",true,false,true));
        given(taskService.findBy(idTask)).willReturn(Optional.of(task));
        doNothing().when(taskService).deleteBy(task.getId());

        this.mockMvc.perform(delete("/v1/Tasks/{id}",task.getId()))
                .andExpect(status().isOk());
    }
}