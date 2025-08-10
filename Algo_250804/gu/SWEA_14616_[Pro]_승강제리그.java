package league;

import java.util.Arrays;

class UserSolution {
	
	int N, L, mAbility[];
	int playersPerLeague;
	Player[][] leagues;
	
	class Player implements Comparable<Player> {
		int id, ability;
		
		Player(int id, int ability) {
			this.id = id;
			this.ability = ability;
		}

		@Override
		public int compareTo(Player o) {
			if (this.ability == o.ability)
				return this.id - o.id;
			return o.ability - this.ability;
		}
	}

    void init(int N, int L, int mAbility[]) {
        this.N = N;
        this.L = L;
        this.mAbility = mAbility;
        
        playersPerLeague = N/L;
        
        leagues = new Player[L][playersPerLeague];
        
        int idx = 0;
        for (int i = 0; i < L; i++) {
        	for (int j = 0; j < playersPerLeague; j++) {
        		leagues[i][j] = new Player(idx, mAbility[idx++]);
        	}
        	Arrays.sort(leagues[i]);
        }
    }

    int move() {
    	int sum = 0;
    	for (int i = 0; i < L-1; i++) {
			Player tmp = leagues[i+1][0];
			leagues[i+1][0] = leagues[i][playersPerLeague-1];
			leagues[i][playersPerLeague-1] = tmp;
			
			sum += leagues[i][playersPerLeague-1].id + leagues[i+1][0].id;
			
			binarySearch(leagues[i], playersPerLeague-1);
			binarySearch(leagues[i+1], 0);
    	}
    	
        return sum;
    }

    int trade() {
    	int sum = 0;
    	int mid = playersPerLeague / 2;
    	for (int i = 0; i < L-1; i++) {
    		Player tmp = leagues[i+1][0];
    		leagues[i+1][0] = leagues[i][mid];
    		leagues[i][mid] = tmp;
    		
    		sum += leagues[i][mid].id + leagues[i+1][0].id;
    		
    		binarySearch(leagues[i], mid);
    		binarySearch(leagues[i+1], 0);
    	}
    	
        return sum;
    }
    
    void binarySearch(Player[] league, int index) {
    	Player[] arr = new Player[playersPerLeague-1];
    	for (int i = 0; i < index; i++) arr[i] = league[i];
    	for (int i = index+1; i < playersPerLeague; i++) arr[i-1] = league[i];
    	
    	int l = 0, r = arr.length-1;
    	while (l <= r) {
    		int m = (l+r) / 2;
    		if (league[index].ability > arr[m].ability) r = m-1;
    		else if (league[index].ability < arr[m].ability) l = m+1;
    		else {
    			if (league[index].id < arr[m].id) l = m-1;
    			else l = m+1;
    			break;
    		}
    		
    	}
    	
    	Player tmp = league[index];
    	for (int i = league.length-1; i > l; i--) {
    		league[i] = league[i-1];
    	}
    	league[l] = tmp;
    }
}
