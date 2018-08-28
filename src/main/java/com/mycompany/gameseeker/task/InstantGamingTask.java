package com.mycompany.gameseeker.task;

import com.mycompany.gameseeker.mongoDB.Result;
import com.mycompany.gameseeker.wrappers.InstantGaming;
import java.util.ArrayList;
import java.util.concurrent.Callable;





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
