package pro.no3;

import java.util.Arrays;

class Pro03 {
	
	Player[][] leagues;
    int N, L;
    
    public class Player implements Comparable<Player> {
        int id;
        int ability;

        Player(int id, int ability) {
            this.id = id;
            this.ability = ability;
        }
        
        @Override
    	public int compareTo(Player o) {
        	if (this.ability != o.ability) {
            	return this.ability - o.ability; 
        	}
        	return this.id - o.id; 
    	}
    }

    void init(int N, int L, int mAbility[]) {
        this.N = N;
        this.L = L;
        leagues = new Player[L][N / L];
        Player[] players = new Player[N];
        
        for (int i = 0; i < N; i++) {
            players[i] = new Player(i, mAbility[i]);
        }
        for (int i = 0; i < L; i++) {
            Player[] temp = new Player[N / L];
            for (int j = 0; j < N / L; j++) {
    			temp[j] = players[i * (N / L) + j];
			}
            Arrays.sort(temp);
            leagues[i] = temp;
        }
    }

    int move() {
        int sum = 0;

        for (int i = 0; i < L; i++) {
            Player best = leagues[i][N / L - 1];
            Player worst = leagues[(i - 1 + L) % L][0];
            sum += best.id + worst.id;
            swap(i, N / L - 1, (i - 1 + L) % L, 0);
        }

        for (int i = 0; i < L; i++) {
        	Arrays.sort(leagues[i]);
        }

        return sum;
    }

    int trade() {
        int sum = 0;
        
        for (int i = 0; i < L; i++) {
           	Player best = leagues[i][N / L - 1];
           	Player middle = leagues[(i - 1 + L) % L][(N / L) / 2];
           	sum += best.id + middle.id;
           	swap(i, N / L - 1, (i - 1 + L) % L, (N / L) / 2);
        }
        
        for (int i = 0; i < L; i++) {
            Arrays.sort(leagues[i]); 
        }
        
        return sum;
    }
    
    void swap(int leagueA, int idxA, int leagueB, int idxB) {
        Player temp = leagues[leagueA][idxA];
        leagues[leagueA][idxA] = leagues[leagueB][idxB];
        leagues[leagueB][idxB] = temp;
    }
}