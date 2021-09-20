import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CopierShould {

    @Mock Source source;
    @Mock Destination destination;

    private Copier copier;

    @BeforeEach
    void setUp() {
        copier = new Copier(source, destination);
    }

    @Test void
    copy_source_to_destination_until_new_line_character_received() {
        when(source.getChar()).thenReturn('A', 'A', 'A', '\n');

        copier.copy();

        verify(destination, times(3)).setChar('A');
    }

}
