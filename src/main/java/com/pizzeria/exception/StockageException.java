package com.pizzeria.exception;

public class StockageException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockageException(String msg) {
		super(msg);
	}
	
	public class SavePizzaException extends StockageException{

		private static final long serialVersionUID = 1L;

		public SavePizzaException(String msg) {
			super(msg);
		}
	}
	
	public class UpdatePizzaException extends StockageException{

		private static final long serialVersionUID = 1L;

		public UpdatePizzaException(String msg) {
			super(msg);
		}
		
	}
	
	public class DeletePizzaException extends StockageException{
		
		private static final long serialVersionUID = 1L;

		public DeletePizzaException(String msg) {
			super(msg);
		}
		
		
	}
	
	
}
