package fedexCILStaging;

public class SD extends BaseInit {

	public static void FedExSD() throws Exception{
		
		//CSR Acknowledge
		Thread.sleep(7000);
		CSR.FedExCSRAcknowledge();
		
		//TC Acknowledge
		Thread.sleep(7000);
		TCAcknowledge.FedExTCAcknowledge();
		
		//Send Pull Alert
		Thread.sleep(7000);
		SendPull.FedExSendPullAlert();
		
		//Rdy for Dispatch
		Thread.sleep(8000);
		ReadyForDispatch.FedExRdyforDispatch();
		
		//Confirm PU alert
		Thread.sleep(8000);
		ConfirmPuSD.FedExConfirmPuAlertSD();
	//---	
		//confirm Pull 3455369
		Thread.sleep(8000);
		ConfirmPull.FedExConfirmPull();
		
		//again confirm Pull
		Thread.sleep(4000);
		PULL.FedExPull();
		
		
		// Pickup
		Thread.sleep(7000);
		Pickup.FedExPickup();
		
		//Drop @ Origin
		Thread.sleep(4000);
		Drop.dropAtOrigin();
		
		//Send Del Alert
		Thread.sleep(3000);
	    SendDelAlert.delAlert();
	
	    //Confirm Del Alert
	    Thread.sleep(7000);
	    ConfirmDel.FedExcomfirmDel();
	    
	  //Wait for Departure
	  		Thread.sleep(5000);
	  		WaitForDeptarture.waitForDept();
	  		
	  		//board
	  		Thread.sleep(5000);
	  		Board.onBoard();
	  		
	  		//XER wait for Arrival
	  		Thread.sleep(5000);
	  		XerWaitForArrival.xerWaitForArr();
	  				
	  		//XER Wait for Departure
	  		Thread.sleep(5000);
	  		XerWaitForDeparture.xerWaitForDept();
	  		
	  		//board1
	  		Thread.sleep(5000);
	  		Board1.onBoard1();
	  		
	  		//Wait for Arrival
	  		Thread.sleep(5000);
	  		WaitForArrival.waitForArr();
	  		
	  		//Recover
	  		Thread.sleep(3000);
	  		Recover.recoverAtDestination();
	  		
	  		//DELIVERED
	  		Thread.sleep(3000);
	  		Deliver.confirmDelivery();
	}
}
