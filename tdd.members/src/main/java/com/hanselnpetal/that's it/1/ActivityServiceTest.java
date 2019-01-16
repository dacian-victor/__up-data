package com.taskboard.application.service;

import com.taskboard.application.util.DtoAssembler;
import com.taskboard.infrastructure.ActivityRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class ActivityServiceTest {

    @Mock
    private DtoAssembler dtoAssembler;
    @Mock
    private ActivityRepository activityRepository;
    @InjectMocks
    ActivityService activityService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void afterUserCreation() {
    }

    @Test
    public void afterMemberAddition() {
    }

    @Test
    public void afterMemberRemoving() {
    }

    @Test
    public void getAllActivities() {
    }
}