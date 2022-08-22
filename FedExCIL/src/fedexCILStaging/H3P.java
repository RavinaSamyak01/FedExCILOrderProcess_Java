package fedexCILStaging;

public class H3P extends BaseInit{
	
	public static void FedExH3P()  throws Exception{
		
		
		//Confirm PU alert
		Thread.sleep(8000);
		ConfirmPu.FedExConfirmPuAlert();
		
		//confirm Pull
		Thread.sleep(8000);
		ConfirmPull.FedExConfirmPull();
		
		//Tender to 3P
		Thread.sleep(8000);
		TenderTo3P.tndrTo3P();
	}

}
