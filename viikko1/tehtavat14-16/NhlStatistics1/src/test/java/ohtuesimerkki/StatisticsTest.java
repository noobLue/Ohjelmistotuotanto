package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsTest {
    Reader readerStub = () -> {
        ArrayList<Player> players = new ArrayList<>();

        players.add(new Player("SuperKalle", "PAA", 9, 9));
        players.add(new Player("Kille", "PAA", 90, 80));
        players.add(new Player("Metzky",   "POL", 60, 43));
        players.add(new Player("Hargvist", "VAR", 21, 22));

        return players;
    };

    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }


    @Test
    public void searchContainsPlayer(){
        Player p = stats.search("SuperKalle");
        assertNotNull(p);
        assertEquals(9, p.getGoals());
        assertEquals(9, p.getAssists());
    }

    @Test
    public void searchIsNull(){
        assertNull(stats.search("Kevin"));
    }

    @Test
    public void teamIsEmpty(){
        List<Player> players = stats.team("POK");
        assertNotNull(players);
        assertEquals(0, players.size());
    }

    @Test
    public void teamContainsTwo(){
        List<Player> players = stats.team("PAA");
        assertNotNull(players);
        assertEquals(2, players.size());
    }


    @Test
    public void topScorersFoundAndOrdered(){
        List<Player> topScorers = stats.topScorers(1);
        assertNotNull(topScorers);
        assertEquals(2, topScorers.size());
        assertEquals("Kille", topScorers.get(0).getName());
        assertEquals("Metzky", topScorers.get(1).getName());
    }
}
