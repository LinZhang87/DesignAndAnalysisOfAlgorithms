// The straightfoward Papadimitriou's algorithm is too slow
// This file contains an implementation that can only determine 
// if 2sat1.txt is satisfiable or not within a reasonable amount of
// time.


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class TwoSat
{
	private static int parseTwoSatInput(String fileName, ArrayList<Integer> clause1_indices, ArrayList<Integer> clause2_indices)
	{
		int N = 0;
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			
			strLine = br.readLine();

			N = Integer.parseInt(strLine);
			
			clause1_indices.add(Integer.MAX_VALUE);
			clause2_indices.add(Integer.MAX_VALUE);
						
			while((strLine = br.readLine()) != null)
			{			
				parse = strLine.split("\\s+", 0);
				if(parse.length != 2)
				{
					System.err.println("Error: Invalid input file");
					br.close();
					return 0;
				}

				clause1_indices.add(Integer.parseInt(parse[0]));
				clause2_indices.add(Integer.parseInt(parse[1]));
			}
			br.close();
			in.close();		
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
		return N;
	}
	
	public static boolean Papadimitriou(ArrayList<Integer> clause1_indices, ArrayList<Integer> clause2_indices)
	{
		//Repeat log2 n times
		int outer_n = (int)(Math.log(clause1_indices.size()) / Math.log(2));
		
		double n = (double)2 * (double)(clause1_indices.size() - 1);// * (double)(clause1_indices.size() - 1);
		
		double inner_n = n;
		boolean[] curr_assign = new boolean[clause1_indices.size()];
		ArrayList<Integer> unsat_indices = new ArrayList<Integer>();
		int unsat_idx, clause_idx;
		Random rand = new Random();
		
		while(outer_n >= 0)
		{
			//choose random initial assignment
			
			for(int i = 1; i < curr_assign.length; i++)
			{
				curr_assign[i] = rand.nextBoolean();
			}
			
			//debug : print random initial assignment
//			System.out.print("initial assignment : ");
//			for(int i = 1; i < curr_assign.length; i++)
//			{
//				System.out.print(curr_assign[i] + " ");
//			}
//			System.out.println();
//			
			//repeat 2n^2 times
			inner_n = n;
			while(inner_n > 0)
			{
				int k1, k2;
				boolean temp1, temp2;
				
				//remove all elements from the previous run
				unsat_indices.clear();
				
				//if current assignment satisfies all clauses, halt and report this 
				for(int j = 1; j < clause1_indices.size(); j++)
				{
					k1 = Math.abs(clause1_indices.get(j));	
					k2 = Math.abs(clause2_indices.get(j));
					temp1 = clause1_indices.get(j) > 0 ? true : false;
					temp2 = clause2_indices.get(j) > 0 ? true : false;
					
					//debug
					//System.out.println("clause " + j + ": " + temp1 + " " + temp2);
					//System.out.println();
					
					//if satisfied, skip checking the 2nd variable in the same clause
					if(temp1 != curr_assign[k1] && temp2 != curr_assign[k2])
					{
						unsat_indices.add(j);
						//System.out.println("clause " + j + ": unsatisfied!");
					}								
				}
				
				//System.out.println();
				
				if(unsat_indices.isEmpty())
				{
					return true;
				}
				else
				{
					//pick arbitrary unsatisfied clauses and flip the value of one of its variables
					unsat_idx = unsat_indices.get(rand.nextInt(unsat_indices.size()));
					//System.out.println("arbitrarily picked clause " + unsat_idx);
					
					//debug
					//System.out.println("Arbitrary picked clause " + );
					
					//choose between the two uniformly at random
					clause_idx = rand.nextInt(2);
					//System.out.println("0->1st var, 1->2nd var:  " + clause_idx);
					
					if(clause_idx == 0)
					{
						k1 = Math.abs(clause1_indices.get(unsat_idx));
						//System.out.println("flipped the 1st var");
						curr_assign[k1] = !curr_assign[k1];
					}
					else
					{
						k2 = Math.abs(clause2_indices.get(unsat_idx));
						//System.out.println("flipped the 2nd var");
						curr_assign[k2] = !curr_assign[k2];
					}
					
//					System.out.print("assignment after flip: ");
//					for(int i = 1; i < curr_assign.length; i++)
//					{
//						System.out.print(curr_assign[i] + " ");
//					}
//					System.out.println("\n");
				}
				
				inner_n--;
			}
			
			outer_n--;
		}
		
		return false;
	}
	
	public static void main(String[] args)
	{
		ArrayList<Integer> clause1_indices = new ArrayList<Integer>();
		ArrayList<Integer> clause2_indices = new ArrayList<Integer>();
		
		//parseTwoSatInput("2sat1.txt", clause1_indices, clause2_indices);
		
		//test code
		/*for(int i = 1; i < clause1_indices.size(); i++)
		{
			System.out.println(clause1_indices.get(i) + "  " + clause2_indices.get(i));
		}*/
		
		//boolean r = Papadimitriou(clause1_indices, clause2_indices);
		
		//System.out.println("2sat1.txt: " + r);
		
		for(int i = 1; i <= 6; i++)
		{
			String f = "2sat" + Integer.toString(i) + ".txt";
			parseTwoSatInput(f, clause1_indices, clause2_indices);
			boolean r = Papadimitriou(clause1_indices, clause2_indices);
			
			System.out.println(f + ": " + r);
			clause1_indices.clear();
			clause2_indices.clear();
		}
	}
}