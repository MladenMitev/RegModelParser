<h1 style="text-align: center;" >RegModel 
</h1> 

<div style="text-align: center;font-weight: bold;" >Enhanced regular expression system. Based on functional-declarative programming.</div>

<br>
<br>RegModel is easy way to looking regular text models.
<br>
<br>RegModel allow you to :
<ul> 
  <li>Matching text expressions.</li>
  <li>Parse any text. And extract what is needed or get the range.</li>
  <li>Fuzzy logic search and match.</li>
  <li>With RegModel it is not need any more to split the input string to many sub string and looking in them.
	</li>
</ul>

<br>
<br>Why RegModel is better than regular expression :
<ul> 
  <li>Easy to undersand.</li>
  <li>You can write a very complicated logic in few word.</li>
	<li>RegModel is very light and much faster technology compared to regular expressions.</li>
	<li>RegModel use small memory and have small footprint.</li>
  <li>RegModel is faser 200-300% then regular expression. Because RegModel not need to compile pattern.</li>
</ul>

<img src="https://jnode.eu/version.jpg">
<br>A typical usage:
<br>&nbsp;&nbsp;&nbsp;String bigString = "Women, from Montreal.";
<br>&nbsp;&nbsp;&nbsp;RegModel regModel = new RegModel(bigString); 
<br>&nbsp;&nbsp;&nbsp;regModel.has("from");  //=> ok, selected model is 'from'
<br>&nbsp;&nbsp;&nbsp;regModel.any();		     //=> ok, selected model is rest to the end ' Montreal.'
<br>&nbsp;&nbsp;&nbsp;regModel.toLeft$();	   //=> change direction to search models from right to the left
<br>&nbsp;&nbsp;&nbsp;regModel.letter();	   //=> ok, selected model is 'from'
<br>&nbsp;&nbsp;&nbsp;regModel.any();		     //=> ok, selected model is rest to the begin 'Women, '
<br>		
<br>or
<br>		
<br>&nbsp;&nbsp;&nbsp;String bigString = "width:   100px;";
<br>&nbsp;&nbsp;&nbsp;RegModel regModel = new RegModel(bigString);
<br>&nbsp;&nbsp;&nbsp;regModel.fromStart$()
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.letter(0,3)	// => ok, selected model is 'wid'; accepted are between 0..3 letters
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.letter(2)	    // => ok, selected model is 'th' accepted are between 1..2 letters
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.char1(':')		// => ok, selected model is ':' accepted one char ':'
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.wspace();		// => ok, selected model is '   ' accepted characters are all white space such:' \n\t\r'
<br>&nbsp;&nbsp;&nbsp;String width = regModel.digits().get();	// => ok, selected model is '100' accepted characters 0..9
<br>&nbsp;&nbsp;&nbsp;String unit = regModel.letter().get();	// => ok, selected model is 'px'

<br>
<br>You can see more information and video examples at <a href="https://jnode.eu/pageapp/RegModel/RegModel.html?from=github">https://jnode.eu/pageapp/RegModel/RegModel.html</a>
