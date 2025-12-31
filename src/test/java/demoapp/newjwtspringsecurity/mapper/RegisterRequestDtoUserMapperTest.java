package demoapp.newjwtspringsecurity.mapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import demoapp.newjwtspringsecurity.auth.dto.mapper.RegisterRequestDtoUserMapper;
import demoapp.newjwtspringsecurity.auth.dto.RegisterRequestDto;
import demoapp.newjwtspringsecurity.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class RegisterRequestDtoUserMapperTest {

    private final RegisterRequestDtoUserMapper mapper =
        Mappers.getMapper(RegisterRequestDtoUserMapper.class);

    @Test
    void toDto_shouldMapUserToRegisterRequestDto() {
        // given
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setFirstName("Test User");
        user.setPassword("secret");

        // when
        RegisterRequestDto dto = mapper.toDto(user);

        // then
        assertNotNull(dto);
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getPassword(), dto.getPassword());
    }

    @Test
    void toEntity_shouldMapRegisterRequestDtoToUser() {
        // given
        RegisterRequestDto dto = new RegisterRequestDto();
        dto.setEmail("test@example.com");
        dto.setFirstName("Test User");
        dto.setPassword("secret");

        // when
        User user = mapper.toEntity(dto);

        // then
        assertNotNull(user);
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getFirstName(), user.getFirstName());
        assertEquals(dto.getPassword(), user.getPassword());
    }
}
