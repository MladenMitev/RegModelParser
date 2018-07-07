package RegModelExamples;

import java.util.function.Consumer;
import fullsoft.RegModel.RegModel;

public class RegModel_ExampleMethods {
		
	public static void main( String args[] ) {
		 new RegModel_ExampleMethods();
	}
	 
	public RegModel_ExampleMethods() {
		any();
		any1();
		char1();
		chars();
		not$();
		can$();
		seek();
		firstOf();
		ifmodifier();
		since();
		wspace1();
		fromStart$();
	}
	 
	private void fromStart$() {
		String bigString = "Women, from Montreal.";
		RegModel regModel = new RegModel(bigString);
		regModel.any();		    //=> ok, by default search inside 'bigString', not from first character
		regModel.has("from");  // => ok, selected model is 'from'
		
		//Move the cursor to the start of string, next model selector have to start from begin of string.
		regModel.fromStart$(); 
		regModel.has("from");  // => err, 'from' is not from begin of 'bigString'
		
		regModel.setOK();  // change state to 'ok'. When state became is 'err', nothing isn't matching
		regModel.has("Women");  // => ok, selected model is 'Women' 
	}
		
	private void any() {
		String bigString = "Women, from Montreal.";
		RegModel regModel = new RegModel(bigString);
		regModel.has("from");  // => ok, selected model is 'from'
		regModel.any();		   //=>  ok, selected model is rest to the end ' Montreal.'
		regModel.toLeft$();	   //=> change direction to search models from right to the left
		regModel.letter();	   // => ok, selected model is 'from'
		regModel.any();		   //=>  ok, selected model is rest to the begin 'Women, '
	}
	
	private void any1() {
		String bigString = "Women.";
		RegModel regModel = new RegModel(bigString);
		regModel.any1(); //=>  ok, selected model is 'W'
		regModel.any1(); //=>  ok, selected model is 'o'
		regModel.any1(); //=>  ok, selected model is 'm'
		regModel.any(2 /*a minimum number of characters to end*/
					, 5 /*a maximum number of characters to end*/ 
					); //=>  ok, selected model is 'en.' Maximum number of characters to end is 3
		regModel.fromStart$();
		regModel.any(7 /*a minimum number of characters to end*/
				, 10 /*a maximum number of characters to end*/ 
				); //=>  err, The remaining characters to the end are not 7, but 6
	}
	
	private void char1() {
		String bigString = "Woooommmen.";
		RegModel regModel = new RegModel(bigString);
		regModel.char1('W'); //=>  ok, selected model is 'W'
		regModel.chars(1,2,"o"); //=>  ok, selected model is 'oo'
		regModel.chars(1,"moe"); //=>  ok, selected model is 'oommme'
	}
	
	private void chars() {
		String bigString = "Woooommmen.";
		RegModel regModel = new RegModel(bigString);
		regModel.chars("moW"); //=>  ok, selected model is 'Woooommm'
		regModel.fromStart$();
		regModel.chars( 0, /*minimum consecutive characters, 0 - means it is ok if is not  */
						3, /*maximum consecutive characters */
						"33"); //=>  ok, selected model is ''
		regModel.chars( 1, /*minimum consecutive characters, 1 - means at least one character  */
				3, /*maximum consecutive characters */
				"moW"); //=>  ok, selected model is 'Woooommm'
	}
	
	private void not$() {
		String bigString = "Height 10 meters";
		RegModel regModel = new RegModel(bigString);
		regModel.fromStart$();
		regModel.not$().digits(); //=>  ok, selected model is 'Height '
		regModel.digits(); //=>  ok, selected model is '10'
	}
	
	private void can$() {
		String bigString = "Height 10 meters";
		RegModel regModel = new RegModel(bigString);
		regModel.fromStart$();
		regModel.can$().digits(); //=>  ok, nothing is selected because the next characters are not digits, but it is ok.
		regModel.can$().not$().digits(); //=>  ok, selected model is 'Height '
	}
	
	private void wspace1(){
		String bigString = "This is CharSequence.";
		RegModel regModel = new RegModel(bigString);
		regModel.letter();
		regModel.wspace();
		regModel.any();
		regModel.wspace1();
		System.out.println(regModel.ok());
	}
	
	public void since(){
		String bigString = "This is CharSequence.Let's use since model for it.";
		RegModel regModel = new RegModel(bigString);
		
		regModel.has("This")
		.since()
			.any()
			.has("CharSequence");
		String sub = regModel.sinceSubstring();
		System.out.println( sub );
	}
	
	public void ifmodifier() {
		String bigString = "This is CharSequence.Let's use if modifier for 4 it.";
		RegModel regModel = new RegModel(bigString);
		regModel.if$()
					.has("modifier") 
					.wspace()		 
					.has("for")		
				.then$()
					.any().number();
					if(regModel.ok()) {
						String ifTrueValue = regModel.get();
						System.out.println("Selected model in then: "+ifTrueValue);
					}
		regModel.else$()
					.fromStart$()
					.has("if")
					.any();
					if(regModel.ok()) {
						String ifFalseValue = regModel.get();
						System.out.println("Selected model in else: "+ifFalseValue);
					}				
		regModel.endIf$();
		
	}
	public void firstOf(){
		String bigString = "This is CharSequence.Let's use firstOf list model for it.";
		RegModel regModel = new RegModel(bigString);
		regModel.firstOf()
					.has(".")
					.has("nothing")
					.has("is")
				.endOf(); //=>  ok,  (2-4) 'is'
		regModel.firstOf()
				.has(".")
				.not$().char1("$")
				.has("is")
			.endOf(); //=>  ok,  (0-1) 'T'
	}
	
	private void seek() {
		String bigString = "height is 10 meters, but height is not too tall.";
		RegModel regModel = new RegModel(bigString);
		regModel.seek(0,2,(r)->{
			regModel.any().has("height").wspace().letter();
		});
		System.out.println( regModel.ok() );
	
	//--------------------------------------------------	
	//	regModel.seek() does something like this below : 
	//--------------------------------------------------	
	//	RegModel regModel = new RegModel(bigString);
	//	int max = 2, min = 0; int count = 0;
	//	while( count<max && regModel.any().has("height").wspace().word().ok() ) {
	//		count++;
	//	}
	//	if(count<min) regModel.err();
	//	if(regModel.err() && count==0 && min==0) regModel.setOK();
	//	System.out.println( regModel.ok() );
		
	}
	 
}
