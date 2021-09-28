package command.requested;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static command.requested.RequestedCommandMapper.map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestedCommandMapperShould {

    @ParameterizedTest
    @CsvSource({
            "POST, posting",
            "READ, reading",
            "FOLLOW, following",
            "WALL, wall"
    })
    void map_command(RequestedCommand expectedRequestedCommand, String command) {
        assertEquals(expectedRequestedCommand, map(command));
    }

    @Test
    void block_mapping_when_unable_to_map_to_requested_command() {
        RequestedCommandNotFoundException exception = assertThrows(
                RequestedCommandNotFoundException.class,
                () -> map("unknown")
        );

        assertEquals("Unable to map to requested command: unknown", exception.getMessage());
    }

}