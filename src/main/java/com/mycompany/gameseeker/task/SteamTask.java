package task;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import utility.Result;
import com.mycompany.gameseeker.wrappers.Steam;

public class SteamTask implements Callable<ArrayList<Result>> {

	private String searchString;
	private Steam st;
	
	public  SteamTask(String searchString) {
		this.searchString = searchString;
		st = new Steam();
	}
	@Override
	public ArrayList<Result> call() throws Exception {
		
		return st.searchResul(searchString);
	}

}
