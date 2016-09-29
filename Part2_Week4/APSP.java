import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class APSP
{
	private static void parseAPSPInput(String fileName, APSPGraph g)
	{		
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			int label1, label2;
			float weight;
			APSPVertex v1, v2;
			APSPEdge newEdge;
			
			strLine = br.readLine();
			parse = strLine.split("\\s+", 0);
			if(parse.length != 2)
			{
				System.err.println("Error: Invalid input file");
				br.close();
				return;
			}
			g.SetNumOfVertices(Integer.parseInt(parse[0]));
			g.SetNumOfEdges(Integer.parseInt(parse[1]));
			
			while((strLine = br.readLine()) != null)
			{			
				parse = strLine.split("\\s+", 0);
				if(parse.length != 3)
				{
					System.err.println("Error: Invalid input file");
					br.close();
					return;
				}

				label1 = Integer.parseInt(parse[0]);
				if(g.GetAllVertices().containsKey(label1) == false)
				{
					v1 = new APSPVertex(label1);
					g.AddVertex(label1, v1);
				}
				else
				{
					v1 = g.GetVertex(label1);
				}
				
				label2 = Integer.parseInt(parse[1]);
				weight = Float.parseFloat(parse[2]);
				
				if(g.GetAllVertices().containsKey(label2) == false)
				{
					v2 = new APSPVertex(label2);
					g.AddVertex(label2, v2);
				}
				else
				{
					v2 = g.GetVertex(label2);
				}
											
				newEdge = new APSPEdge(v1, v2, weight);
				v1.AddEdge(newEdge);
			}
			br.close();
			in.close();			
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static Float FloydWarshall(APSPGraph g)
	{
		int n = g.GetNumOfVertices();
		float [][][] subs = new float[n+1][n+1][n+1];
		
		for(int i = 1; i <= n; i++)
		{
			for(int j = 1; j <= n; j++)
			{
				if(i == j)
				{
					subs[i][j][0] = 0;
				}
				else
				{
					subs[i][j][0] = g.ReturnEdgeCost(i, j);
				}
			}
		}
		
		for(int k = 1; k <= n; k++)
		{
			for(int i = 1; i <= n; i++)
			{
				for(int j = 1; j <= n; j++)
				{
					subs[i][j][k] = Math.min(subs[i][j][k-1], subs[i][k][k-1]+subs[k][j][k-1]);
				}
			}
		}
				
		//Check for negative cost cycle
		for(int i = 1; i <= n; i++)
		{
			if(subs[i][i][n] < 0)
			{
				return null;
			}
		}
		
		//No negative cost cycle
		ArrayList<Float> results = new ArrayList<Float>();
		for(int i = 1; i <= n; i++)
		{
			for(int j = 1; j <= n; j++)
			{
				results.add(subs[i][j][n]);
			}
		}
		
		Collections.sort(results);
		return results.get(0);
	}
		
	public static void main(String[] args)
	{
		APSPGraph g1 = new APSPGraph();
		parseAPSPInput("g3.txt", g1);
		Float r = FloydWarshall(g1);
		System.out.println(r);
		parseAPSPInput("numOfPaths.txt", g1);
	}
}