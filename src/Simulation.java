

public class Simulation {
	
	private int totalWars;
	private int totalWarsDub;
	private int totalBattles;
	
	private double avgBattlesPerGame;
	private double avgWarsPerGame;
	private double avgWarsDoublePerGame;
	private double maxNumBattlesInGame;
	private double minNumBattlesInGame;
	private double maxNumWarsInGame;
	private double minNumWarsInGame;
	
	public Simulation(int games){
		double[] battlesArr = new double[games];
		double[] warsArr = new double[games];
		double[] warsDubArr = new double[games];
		totalWars = 0;
		totalWarsDub = 0;
		totalBattles = 0;
		maxNumBattlesInGame = 0;
		maxNumWarsInGame = 0;
		for(int i = 0; i < games; ++i){
			Game game = new Game();
			game.play();
			battlesArr[i] = game.getBattles();
			warsArr[i] = game.getWars();
			warsDubArr[i] = game.getWarsDub();
			if(battlesArr[i] >= maxNumBattlesInGame){ //initializes maxBattles and minBattles (minBattles included in order to make first value)
				minNumBattlesInGame = maxNumBattlesInGame;
				maxNumBattlesInGame = battlesArr[i];
			}
			if (battlesArr[i] <= minNumBattlesInGame){ //re declares minBattles to a lesser value
				minNumBattlesInGame = battlesArr[i];
			}
			if(warsArr[i] >= maxNumWarsInGame){
				minNumWarsInGame = maxNumWarsInGame;
				maxNumWarsInGame = warsArr[i];
			}
			if (warsArr[i] <= minNumWarsInGame){
				minNumWarsInGame = warsArr[i];
			}
			totalBattles += battlesArr[i];
			totalWars += warsArr[i];
			totalWarsDub += warsDubArr[i];
		}
		compute(games, totalBattles, totalWars, totalWarsDub);
		report();
	}
	
	public void compute(int games, int battles, int wars, int warsDub){
		avgBattlesPerGame = (double) battles / games;
		avgWarsPerGame = (double) wars / games;
		avgWarsDoublePerGame = (double) warsDub / games;
	}
	
	public void report(){
		System.out.println("Average Battles Per Game: " + avgBattlesPerGame);
		System.out.println("Average Wars Per Game: " + avgWarsPerGame);
		System.out.println("Average War Doubles Per Game: " + avgWarsDoublePerGame);
		System.out.println("Maximum Number of Battles in a Game: " + maxNumBattlesInGame);
		System.out.println("Minimum Number of Battles in a Game: " + minNumBattlesInGame);
		System.out.println("Maximum Number of Wars in a Game: " + maxNumWarsInGame);
		System.out.println("Minimum Number of Wars in a Game: " + minNumWarsInGame);

	}
	
	public static void main(String[] args){
		int games = Integer.parseInt(args[0]);
		Simulation simulate = new Simulation (games);
	}
	
}