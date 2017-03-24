package ohtuesimerkki;

import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
	
	Reader readerStub = new Reader() {
		
		@Override
		public List<Player> getPlayers() {
			ArrayList<Player> players = new ArrayList<Player>();
			
			players.add(new Player("Semenko", "EDM", 4, 12));
			players.add(new Player("Lemieux", "PIT", 45, 54));
			players.add(new Player("Kurri", "EDM", 37, 53));
			players.add(new Player("Yzerman", "DET", 42, 56));
			players.add(new Player("Gretzky", "EDM", 35, 89));
			
			return players;
		}
	};
	
	Statistics stats;
	
	public StatisticsTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		stats = new Statistics(readerStub);
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void hakuToimii() {
		assertEquals(stats.search("Yzerman").getName(), "Yzerman");
		assertEquals(stats.search("Yzerman").getAssists(), 56);
		assertEquals(stats.search("Yzerman").getGoals(), 42);
		assertEquals(stats.search("Yzerman").getTeam(), "DET");
		
		assertNull(stats.search("Koivu"));
	}
	
	@Test
	public void teamToimii() {
		assertTrue(stats.team("EDM").contains(stats.search("Semenko")));
		assertTrue(stats.team("EDM").contains(stats.search("Kurri")));
		assertTrue(stats.team("EDM").contains(stats.search("Gretzky")));
		
		assertFalse(stats.team("EDM").contains(stats.search("Lemieux")));
	}
	
	@Test
	public void topScorersToimii() {
		assertTrue(stats.topScorers(3).contains(stats.search("Yzerman")));
		assertFalse(stats.topScorers(3).contains(stats.search("Semenko")));
	}
}
