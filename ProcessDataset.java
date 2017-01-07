
import java.io.*;
import java.util.*;

public class ProcessDataset {

	static Integer noOfAuthors = 1314050;
	static Integer noOfNewEdgeToBeAddedForIncreasedDensity = 0;
	static Map<String, Graph> graphMap = new HashMap<String,Graph>();

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		String inputFileName = (args.length>0)?args[0]:"dblp_coauthor_dataset";
		String str = (args.length>0)?args[1]:"0";
		Integer densifyByEdges = Integer.parseInt(str); 

		readInput(inputFileName);
		if(densifyByEdges != 0)
		{
	    	System.out.println("No_of_record_Before_Densification:"+graphMap.size());
			desifyDataset(densifyByEdges);
	    	System.out.println("No_of_record_After_Densification:"+graphMap.size());
		}
		writeDataToFile(inputFileName);		
	}

	
	public static void readInput(String filename) throws FileNotFoundException,IOException 
	{
    	FileInputStream fileInputStream = new FileInputStream(filename);
    	DataInputStream dataInputStream = new DataInputStream(fileInputStream);
   		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
   		String strLine;
    	int count = 0;

    	while ((strLine = bufferedReader.readLine()) != null && strLine.length()!=0){// && count<100){
         	count++;
         	if(count==1)continue;
            StringTokenizer st = new StringTokenizer(strLine, "\t");  
            ArrayList<Integer> intArr = new ArrayList<Integer>();
            while (st.hasMoreElements()) {
            	String element = ((String)st.nextElement());
            	intArr.add(Integer.parseInt(element));
            }
            
            if(graphMap.isEmpty())
            {
             	Graph g = new Graph(intArr.get(0),intArr.get(1),intArr.get(2));
             	graphMap.put(g.get_graphId(),g);
            }
            else
            {
            	String graphId = createGraphId(intArr.get(0),intArr.get(1));
              	if(graphMap.containsKey(graphId))
              	{
              		Graph g = graphMap.get(graphId);
                  	Graph newG = new Graph(intArr.get(0),intArr.get(1),(g.get_edgeWeight()+1));
                 	graphMap.replace(graphId, g, newG);
              		continue;
              	}
              	else
              	{
                  	Graph g = new Graph(intArr.get(0),intArr.get(1),intArr.get(2));
                 	graphMap.put(g.get_graphId(),g);
              	}
            } 
            
            intArr=null;
    	}		
    	dataInputStream.close(); 
    	fileInputStream = null;
    	dataInputStream = null;
    	bufferedReader = null;
    	
    	System.out.println("No_of_record_read:"+ (count-1));
    	System.out.println("No_of_record_Used:"+graphMap.size());
	}

	public static void writeDataToFile(String filename) throws FileNotFoundException,IOException 
	{	
		try
		{
			FileWriter fw = new FileWriter(filename,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.write("");
			pw.flush(); 
			pw.close();
			
			File file =new File(filename);
     		if(!file.exists()){
    			file.createNewFile();
     		}
     		
//     		FileWriter fw = new FileWriter(file,true);
	    	BufferedWriter bw = new BufferedWriter(fw);
	        pw = new PrintWriter(bw);
	    	Set<?> set = graphMap.entrySet();
	    		    	
			String data = noOfAuthors+"\t"+noOfAuthors+"\t"+graphMap.size();
	        pw.append(data);
        	pw.println("");

	    	int count=0;
  	      	Iterator iterator = set.iterator();
  	      	while(iterator.hasNext()) {
  	      		Map.Entry m = (Map.Entry)iterator.next();
  	      		Graph g = (Graph) m.getValue();
				data = g.edgeList();
		        pw.append(data);
	        	count++;

		        if(count!=graphMap.size()){
		        	pw.println("");
		        }
  	      	}
  	       pw.close();
		} 
		catch(IOException ioe)
		{
			System.out.println("Exception occurred:");
	    	ioe.printStackTrace();
		}
	}
	
	public static void desifyDataset(Integer byNoOfEdges)
	{
		for(int i=0; i<byNoOfEdges; i++)
		{
			boolean createdNewConnection = false;
			while(!createdNewConnection)
			{
				//1. Pick a random author from list of authors
				int randomAuthorOne = randomInteger(1,noOfAuthors);
				int randomAuthorTwo = randomInteger(1,noOfAuthors);
				
				boolean bothAreSameAuthor = (randomAuthorOne == randomAuthorTwo)?true:false;
				while(bothAreSameAuthor){
					randomAuthorTwo = randomInteger(1,noOfAuthors);
					bothAreSameAuthor = (randomAuthorOne == randomAuthorTwo)?true:false;
				}
					
				//2.get all coauthors for the given randomAuthor picked
				String graphId =  createGraphId(randomAuthorOne,randomAuthorTwo);
	          	if(graphMap.containsKey(graphId))
	          	{
	          		continue;
	          	}
	          	else
	          	{
	              	Graph newG = new Graph(randomAuthorOne,randomAuthorTwo,1);
                 	graphMap.put(newG.get_graphId(),newG);
                 	createdNewConnection = true;
	          	}
			}			
		}
	}
	
	
	public static String createGraphId(Integer node_from, Integer node_to)
	{
	    return node_from+"_"+node_to;    
	}

	public static int randomInteger(int min, int max) 
	{

		Random rand = new Random();  
		int randomNum = rand.nextInt(max-min+1) + min; 
		return randomNum;
	}
}

class  Graph {
    
	private String graphId;
	private Integer node_from;
	private Integer node_to;
	private Integer edgeWeight;
 
    public Graph(){
    }
    
    public Graph(Integer node_from, Integer node_to, Integer edgeWeight){
        this.node_from = node_from;
        this.node_to = node_to;
        this.edgeWeight = edgeWeight;
        this.graphId = nodeToNode();
    }
     
    public Integer get_node_from() { return node_from; }
    public Integer get_node_to() {  return node_to; }
    public Integer get_edgeWeight() { return edgeWeight;}
    public String get_graphId() { return graphId;}

    public void set_node_from(Integer node_from) {  this.node_from = node_from; }     
    public void set_node_to(Integer node_to) {  this.node_to = node_to; }
    public void set_edgeWeight(Integer edgeWeight) { this.edgeWeight = edgeWeight; }
    public void set_graphId(String graphId) { this.graphId = graphId; }

    public String nodeToNode(){ return node_from+"_"+node_to; }
    public String edgeList(){   return node_from+"\t"+node_to+"\t"+edgeWeight;}
}













