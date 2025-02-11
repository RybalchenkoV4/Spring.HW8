package org.example.taskMaster.service;

import org.example.taskMaster.aspect.TrackUserAction;
import org.example.taskMaster.model.Task;
import org.example.taskMaster.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTask() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return  repository.findById(id).orElse(null);
    }

    @TrackUserAction
    public Task saveTask(Task task) {
        return repository.save(task);
    }

    @TrackUserAction
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @TrackUserAction
    public Task updateTask(Long id, Task taskDetails) {
        Task task = repository.findById(id).orElse(null);
        if(task != null) {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.isCompleted());
            return repository.save(task);
        }
        return null;
    }

}
