package fedexCILStaging;

public class LOC extends BaseInit{
	
	public static void FedExLOC() throws Exception {
		Thread.sleep(4000);
		
		//CSR Acknowledge
		Thread.sleep(7000);
		CSR.FedExCSRAcknowledge();
		
		//TC Acknowledge
		Thread.sleep(7000);
		TCAcknowledge.FedExTCAcknowledge();
		
		//Rdy for Dispatch
		Thread.sleep(8000);
		ReadyForDispatch.FedExRdyforDispatch();
		
		//Confirm PU alert
		Thread.sleep(8000);
		ConfirmPuLOC.FedExConfirmPuAlertLOC();
		
		// Pickup
		Thread.sleep(7000);
		Pickup.FedExPickup();
	}

}
