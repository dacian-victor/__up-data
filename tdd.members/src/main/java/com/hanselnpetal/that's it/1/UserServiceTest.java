package com.taskboard.application.service;

import com.taskboard.application.dto.UserDTO;
import com.taskboard.application.exception.BoardNotFoundException;
import com.taskboard.application.exception.UserNotFoundException;
import com.taskboard.application.util.DtoAssembler;
import com.taskboard.config.LambdaMatcher;
import com.taskboard.domain.entity.User;
import com.taskboard.infrastructure.UserRepository;
import org.assertj.core.api.ObjectAssert;
import org.assertj.core.matcher.AssertionMatcher;
import org.hamcrest.BaseMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
//        verify(repository).save(captor.capture());
//        assertEquals(captor.getValue().getName(), CREATED_NAME);
//        assertEquals(captor.getValue().getLoginId(), CREATED_UUID);
//        verify(repository).save(argThat(new AssertionMatcher<User>() {
//                    @Override
//                    public void assertion(User actual) throws AssertionError {
//                        assertThat(actual)
//                                .hasFieldOrPropertyWithValue("loginId", CREATED_UUID)
//                                .hasFieldOrPropertyWithValue("name", CREATED_NAME);
//                    }
//                })
//        );
//    }

//    @RunWith(Enclosed.class)
//    public class Create {
//
//        @Before
//        public void init() {
//
//        }
//
//        @Test
//        public void create_ShouldCreateUserEntry() {
//
//        }
//
//        @Test
//        public void create_ShouldReturnUserEntry() {
//
//        }
//    }

/**
 * @author dacian.victor
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String CREATED_UUID = "1111.1111.1111.1111";
    private static final String CREATED_NAME = "login.name";
    private static final Long ID = 20L;

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void create_ShouldCreateUserEntry() {
        //given
        when(repository.save(isA(User.class)))
                .thenReturn(new User(CREATED_UUID, CREATED_NAME, null));
        //when
        service.createUser(new UserDTO(CREATED_UUID, CREATED_NAME));
        //then
        verify(repository).save(argThat((User arg) ->
                arg.getName().equals(CREATED_NAME) &&
                        arg.getLoginId().equals(CREATED_UUID)));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void create_ShouldReturnUserEntry() {
        //given
        when(repository.save(isA(User.class)))
                .thenReturn(new User(CREATED_UUID, CREATED_NAME, null));
        //when
        UserDTO created = service.createUser(new UserDTO(CREATED_UUID, CREATED_NAME));
        //then
        assertThat(created.getName()).isEqualTo(CREATED_NAME);
        assertThat(created.getLoginId()).isEqualTo(CREATED_UUID);
    }

    @Test
    public void findAll_ShouldReturnEntryList() {
        //given
        when(repository.findAll())
                .thenReturn(
                        Arrays.asList(new User(CREATED_UUID, CREATED_NAME, null),
                                new User(CREATED_UUID, CREATED_NAME, null)));
        //when
        List<UserDTO> userEntries = service.findAll();
        //then
        assertThat(userEntries).hasSize(2);
        assertThat(userEntries).contains(new UserDTO(CREATED_UUID, CREATED_NAME));
        assertThat(userEntries.get(0).getName()).isEqualTo(CREATED_NAME);
        assertThat(userEntries.get(0).getLoginId()).isEqualTo(CREATED_UUID);
    }


    @Test
    public void findById_ShouldReturnFoundUserEntry() {
        given(repository.findById(ID))
                .willReturn(Optional.of(new User(CREATED_UUID, CREATED_NAME, null)));
        UserDTO entry = service.findById(ID);
        verify(repository).findById(any());
        verifyNoMoreInteractions(repository);
        assertThat(entry.getName()).isEqualTo(CREATED_NAME);
        assertThat(entry.getLoginId()).isEqualTo(CREATED_UUID);
    }

    @Test(expected = UserNotFoundException.class)
    public void findById_ShouldThrowNotFoundException() {
        given(repository.findById(ID)).willReturn(Optional.empty());
        service.findById(ID);
    }

    @Test
    public void findByName_ShouldReturnFoundUserEntry() {
        given(repository.findByName(CREATED_NAME))
                .willReturn(Optional.of(new User(CREATED_UUID, CREATED_NAME, null)));
        UserDTO entry = service.findByName(CREATED_NAME);
        verify(repository).findByName(any());
        verifyNoMoreInteractions(repository);
        assertThat(entry.getName()).isEqualTo(CREATED_NAME);
        assertThat(entry.getLoginId()).isEqualTo(CREATED_UUID);

        when(repository.findByName(CREATED_NAME))
                .thenReturn(Optional.of(new User(CREATED_UUID, CREATED_NAME, null)));
        verify(repository).findByName(eq(CREATED_NAME));
    }

    @Test(expected = UserNotFoundException.class)
    public void findByName_ShouldThrowNotFoundException() {
        given(repository.findByName(CREATED_NAME)).willReturn(Optional.empty());
        service.findByName(CREATED_NAME);
    }
}