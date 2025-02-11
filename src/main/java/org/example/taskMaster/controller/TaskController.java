package org.example.taskMaster.controller;

import org.example.taskMaster.model.Task;
import org.example.taskMaster.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", service.getAllTask());
        return "tasks";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        service.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = service.getTaskById(id);
        model.addAttribute("task", task);
        return "task-form";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task, Model model) {
        Task updateTask = service.updateTask(id, task);
        if(updateTask != null) {
            return "redirect:/tasks";
        } else {
            model.addAttribute("error", "Task not found");
            return "task-form";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}
