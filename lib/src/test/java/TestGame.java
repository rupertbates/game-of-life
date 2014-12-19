import com.example.Game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGame extends Game{

    public TestGame() {
        super(10);
    }

    @Test
    public void GetNeighboursShouldReturn8Cells(){
        assertEquals(8, getNeighbours(0, 0).size());
    }
}
