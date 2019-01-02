import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
class Records
{
      int index,attr1;
      String attr2;
}
public class processor {

	static Records[] rc = new Records[10];
	static ArrayList<Records> male = new ArrayList<>();
	static ArrayList<Records> female = new ArrayList<>();
	static ArrayList<Records> stratifiedSample = new ArrayList<>();
	public static void main(String args[]) throws FileNotFoundException,IOException
	{
		String path = "data.csv";
		BufferedReader CSV = new BufferedReader(new FileReader(new File(path)));
		String data = CSV.readLine();
		int i = 0, min = Integer.MAX_VALUE,max = Integer.MIN_VALUE; System.out.println("Dataset:");
		while(data!=null)
		{
			rc[i]=new Records();
			String[] dataArray=data.split(","); 
			rc[i].index=Integer.parseInt(dataArray[0]);
			rc[i].attr1=Integer.parseInt(dataArray[1]); 
			rc[i].attr2=dataArray[2];
			
			if(rc[i].attr1 > max)
				max=rc[i].attr1; 
			if(rc[i].attr1 < min)
				min=rc[i].attr1;
			
			if(rc[i].attr2.equalsIgnoreCase("M"))
				male.add(rc[i]);
			else
				female.add(rc[i]);
			System.out.println(rc[i].index+" "+rc[i].attr1+" "+rc[i].attr2);
			data=CSV.readLine();
			i++;
		}
        //finding aggregate for numeric attribute
		System.out.println("\nAggregation Results:\n");
        int avg =0;
        
        for(int j=0;j<i;j++)
        	avg+= rc[j].attr1; 
        avg= avg/i;
		System.out.println("max value :"+max+"\tmin value:"+min); 
		System.out.println("Average value is: "+avg);
		
		//Discretization
		System.out.println("\nDataset after discretization:");
		for(int j=0;j<i;j++)
		{
				String discreteValue = "";
				int value = rc[j].attr1;
		        if(value >90)
		            discreteValue = "A";
		        else if(value > 80)
		            discreteValue = "B";
		        else if(value >70)
		            discreteValue = "C";
		        else if(value >60)
		            discreteValue = "D";
		        else if(value >50)
		            discreteValue = "E";
		        else
		            discreteValue = "F";
		       
				System.out.println(rc[j].index+" "+discreteValue+" "+rc[j].attr2);
		}
		
		//Sampling
		int maleSize = male.size();
		int femaleSize = female.size();
		int n =i;
		int sampsize = 3;
		int fclass_samp_size = (int)Math.rint( (sampsize / (float)n) * femaleSize );
		Collections.shuffle(female);
        for(int j = 0 ; j < fclass_samp_size; j++)
            stratifiedSample.add(female.get(j));
		int mclass_samp_size = (int)Math.rint( (sampsize / (float)n) * maleSize );
		Collections.shuffle(male);
        for(int j = 0 ; j < mclass_samp_size; j++)
            stratifiedSample.add(male.get(j));
		
		for(Records r: stratifiedSample)
			System.out.println(r.index+" "+r.attr1+" "+r.attr2);
	
		CSV.close();
	}
}
