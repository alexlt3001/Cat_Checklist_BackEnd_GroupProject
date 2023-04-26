package com.example.Cat_CheckList_BackEnd_GroupProject.services;

import com.example.Cat_CheckList_BackEnd_GroupProject.models.Cat;
import com.example.Cat_CheckList_BackEnd_GroupProject.models.Task;
import com.example.Cat_CheckList_BackEnd_GroupProject.models.TaskDTO;
import com.example.Cat_CheckList_BackEnd_GroupProject.models.TaskType;
import com.example.Cat_CheckList_BackEnd_GroupProject.repositories.CatRepository;
import com.example.Cat_CheckList_BackEnd_GroupProject.repositories.TaskRepository;
import com.example.Cat_CheckList_BackEnd_GroupProject.repositories.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServices {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CatRepository catRepository;

    @Autowired
    TaskTypeRepository taskTypeRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id).get();
    }

    public Task saveNewTask(TaskDTO taskDTO) {
        Cat cat = catRepository.findById(taskDTO.getCatId()).get();
        TaskType taskType = taskTypeRepository.findById(taskDTO.getTaskTypeId()).get();
        Task task = new Task(taskDTO.getContent(), taskDTO.isCompleted(), taskDTO.getDueDate(),taskDTO.getPriority(), cat , taskType);
        return taskRepository.save(task);
    }

    public Task updateTask(TaskDTO taskDTO, Long id) {
        Task task = taskRepository.findById(id).get();
        task.setContent(taskDTO.getContent());
        task.setCompleted(taskDTO.isCompleted());
        task.setDueDate(taskDTO.getDueDate());
        task.setPriority(taskDTO.getPriority());
        return taskRepository.save(task);
    }

    public Task markAsCompleted(Long id, Boolean aBoolean) {
        Task task = taskRepository.findById(id).get();
        task.setCompleted(true);
        return taskRepository.save(task);
    }


    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}
