package player;

public class Player  implements Comparable<Player>{
	public String username,account,passwd;
	public int rank,score;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public Player(String username,String account,String passwd,int rank,int score){
		this.username=username;
		this.account=account;
		this.passwd=passwd;
		this.rank=rank;
		this.score=score;
		
	}
	
public Player() {
		
	}

@Override
public int compareTo(Player p) {
	if(score > p.getScore()) {
		return -1;
	}else if(score == p.getScore()) {
		return 0;
	}
	return 1;
}
	
	
	

}
