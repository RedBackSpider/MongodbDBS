import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class dbquery
{
    public static void main(String []args)
    {
	String textQuery = "";
        String ps = "";
        int pagesize = 0;
	if(args.length != 2)
	    {
		System.out.println("Incorrect number of Arguments");
		System.exit(0);
	    }
        boolean numFound = false;
	try
	{
	    pagesize = Integer.parseInt(args[0]);
	    textQuery = args[1];
	    numFound = true;
	}
	catch(NumberFormatException e)  
	{  
	    numFound = false;  
	} 
	if(!numFound)
	{
	    try
	    {
		pagesize = Integer.parseInt(args[1]);
		textQuery = args[0];
	    }
        catch(NumberFormatException e)
	    {
		System.out.println("Non-numerical pagesize entered");
		System.exit(0);
	    }
	}
        String inputname = "heap." + pagesize;
	// Searching through BNName: textQuery
	// Amount of bytes to read at a time = page size:
	ArrayList<TestPage> UnfilledPages = new ArrayList<>();
        try
	    {
		DataInputStream is = new DataInputStream(new FileInputStream(inputname));
		long startTime = System.currentTimeMillis();
		long numOfRecords = 0;
		byte[] buffer = new byte[pagesize + 8];
		int offset = 0;
		int len = pagesize;
		int reccount = 0;
		TestRecord correctRecord; // record to be assigned
		boolean RecordFound = false;
		while((is.read(buffer,offset,len + 4)) != -1 && !RecordFound) //While there are still bytes to be found and a record has not been found
		{
		    byte[] slice = Arrays.copyOfRange(buffer, 0, 8);
		    System.out.println(buffer);
		    reccount = ((slice[0] & 0xFF) << 56) | ((slice[1] & 0xFF) << 48)
			| ((slice[2] & 0xFF) << 40) | ((slice[3] & 0xFF) << 32) | ((slice[4] & 0xFF) << 24) | ((slice[5] & 0xFF) << 16)
			| ((slice[6] & 0xFF) << 8) | (slice[7] & 0xFF);
		    break;
		    /*ArrayList<String> linetokens = new ArrayList<String>(Arrays.asList(line.split("\t", -1)));
		    for(int i = 0; i < linetokens.size(); i++)
		    {
			if(linetokens.get(i).charAt(0) == '"')
			{
			    linetokens.set(i, linetokens.get(i).substring(1,linetokens.get(i).length()));
			}
			if(linetokens.get(i).charAt(linetokens.get(i).length()-1) == '"')
			{
			    linetokens.set(i, linetokens.get(i).substring(0,linetokens.get(i).length()-1));
			}
		    }
		    TestRecord test = new TestRecord(linetokens.get(0), linetokens.get(1), linetokens.get(2));
		    if(test.getByteSize() > pagesize)
		    {
			System.out.println("Record cannot fit into page structure, increase page size");
			is.close();
			System.exit(0);
		    }
		    Iterator<TestPage> iter = UnfilledPages.iterator();
		    boolean inserted = false;
		    
		    TestPage page = iter.next();
		    inserted = page.insertRecord(test);
		    if(!inserted)
			{
			    TestPage page = new TestPage(pagesize);
			    page.insertRecord(test);
			    UnfilledPages.add(page);
			}
		    Collections.sort(UnfilledPages, new Comparator<TestPage>() {
			    public int compare(TestPage p1, TestPage p2) {
				return p2.getBytesFilled() - p1.getBytesFilled();
			    }
			});
		    numOfRecords++;
		    */
		    /*if(linetokens.get(8).length() != 0)
		      {
                      abn = Long.parseLong(linetokens.get(8));
		      }*/
		}
		System.out.println(reccount);
		is.close();
		/*
		    Iterator<TestPage> it = UnfilledPages.iterator();
		    while(it.hasNext())
			{
			TestPage page = it.next();
			System.out.println("New Page Bytes:" + page.getBytesFilled());
			ArrayList<TestRecord> records = page.getRecords();
			Iterator<TestRecord> it2 = records.iterator();
			int pageByteCount = page.getBytesFilled();
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			byte[] writeBytes = new byte[pagesize];
			while(it2.hasNext())
			    {
				TestRecord record = it2.next();
				byte[] recordBytes;
				//System.out.println(record.getID());
				//System.out.println(record.getCode());
				//System.out.println(record.getName());
				byte[] id = record.getID().getBytes();
				byte[] code = record.getCode().getBytes();
				byte[] name = record.getName().getBytes();
				byte[] namelength = new byte[4];
				int nl = record.getName().length();
				namelength[0] = (byte) (nl >> 24);
				namelength[1] = (byte) (nl >> 16);
				namelength[2] = (byte) (nl >> 8);
				namelength[3] = (byte) (nl);
				bytestream.write(id);
				bytestream.write(code);
				bytestream.write(namelength);
				bytestream.write(name);
			    }
			writeBytes = bytestream.toByteArray();
			byte[] paddingBytes = new byte[pagesize-writeBytes.length];
			os.write(writeBytes,0,writeBytes.length);
			os.write(paddingBytes,0,paddingBytes.length);
		    }
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		System.out.println("Time taken in milliseconds: " + duration);
		System.out.println("Number of records inserted: " + numOfRecords);
		System.out.println("Numbeer of pages made: " + UnfilledPages.size());
		*/
	    }
	catch(IOException e)
	    {
		System.exit(0);
	    }
    }
}