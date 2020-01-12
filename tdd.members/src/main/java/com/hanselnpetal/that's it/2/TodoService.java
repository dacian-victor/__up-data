package com.example.data.auditing.tdd.service;

import com.example.data.auditing.tdd.common.TodoMapper;
import com.example.data.auditing.tdd.domain.Todo;
import com.example.data.auditing.tdd.domain.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TodoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);

    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TodoDTO create(TodoDTO newTodoEntry) {
        LOGGER.info("Creating a new todo entry by using information: {}", newTodoEntry);

        Todo created = TodoMapper.mapDTOIntoEntity(newTodoEntry);

        created = repository.save(created);
        LOGGER.info("Created a new todo entry: {}", created);

        return TodoMapper.mapEntityIntoDTO(created);
    }

    @Transactional
    public TodoDTO delete(Long id) {
        LOGGER.info("Deleting a todo entry with id: {}", id);

        Todo deleted = findTodoEntryById(id);
        LOGGER.debug("Found todo entry: {}", deleted);

        repository.delete(deleted);
        LOGGER.info("Deleted todo entry: {}", deleted);

        return TodoMapper.mapEntityIntoDTO(deleted);
    }

    @Transactional
    public List<TodoDTO> findAll() {
        LOGGER.info("Finding all todo entries.");

        List<Todo> todoEntries = StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        LOGGER.info("Found {} todo entries", todoEntries.size());

        return todoEntries.stream().map(t->TodoMapper.mapEntityIntoDTO(t)).collect(Collectors.toList());
    }

    @Transactional
    public TodoDTO findById(Long id) {
        LOGGER.info("Finding todo entry by using id: {}", id);

        Todo todoEntry = findTodoEntryById(id);
        LOGGER.info("Found todo entry: {}", todoEntry);

        return TodoMapper.mapEntityIntoDTO(todoEntry);
    }

    @Transactional
    public TodoDTO update(TodoDTO updatedTodoEntry) {
        LOGGER.info("Updating the information of a todo entry by using information: {}", updatedTodoEntry);

        Todo updated = findTodoEntryById(updatedTodoEntry.getId());
        updated.setName(updatedTodoEntry.getName());
        updated.setType(updatedTodoEntry.getType());

        //We need to flush the changes or otherwise the returned object
        //doesn't contain the updated audit information.
        repository.flush();

        LOGGER.info("Updated the information of the todo entry: {}", updated);

        return TodoMapper.mapEntityIntoDTO(updated);
    }

    private Todo findTodoEntryById(Long id) {
        Optional<Todo> todoResult = repository.findById(id);
        return todoResult.orElseThrow(() -> new TodoNotFoundException(id));
    }
}
