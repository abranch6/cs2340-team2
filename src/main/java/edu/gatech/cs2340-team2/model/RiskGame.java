import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class RiskGame {
	
	Queue list = new LinkedList();
	int armies;
	
	public Queue players(String[] names){
		Random rand = new Random();
		while(list.size() < names.length){
			int index = rand.nextInt(names.length);
			if (names[index] != null){
				list.add(names[index]);
				names[index] = null;
			}
		}
		return list;
	}
	public int armies(){
		switch (list.size()){
		case 3: armies = 35;
			break;
		case 4: armies = 30;
			break;
		case 5: armies = 25;
			break;
		case 6: armies = 20;
			break;
		}
		return armies;
	}
		
}

