package task;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import utility.Result;
import wrappers.InstantGaming;



public class InstantGamingTask implements Callable<ArrayList<Result>> {

	private String searchString;
	private InstantGaming ig;
	
	public  InstantGamingTask(String searchString) {
		this.searchString = searchString;
		ig = new InstantGaming();
	}
	@Override
	public ArrayList<Result> call() throws Exception {
		// TODO Auto-generated method stub
		return ig.searchResults(searchString);
	}

}
