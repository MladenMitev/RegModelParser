# RegModel 


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

##Why RegModel is better than regular expression :
- Easy to undersand.
- You can write a very complicated logic in few word.</li>
- RegModel is very light and much faster technology compared to regular expressions.</li>
- RegModel use small memory and have small footprint.</li>
- RegModel is faser 200-300% then regular expression. Because RegModel not need to compile pattern.</li>



```java
//                                 111
//Character position :   0123456789012
//Input string       :  'width: 100px;'
______________________________________
RegModelDebug regModel = new RegModelDebug("width: 100px;");
String property = regModel                    // range(0->0)  selected model ''
                  .word().get();              // range(0->5)  selected model 'width'
String value = regModel.char1(':')            // range(5->6)  selected model ':'
                  .wspace0()                  // range(6->7)  selected model ' '
                  .number().get();            // range(7->10)  selected model '100'
String unit = regModel.word().get();          // range(10->12)  selected model 'px'

```

<br>
<br>You can see more information and video examples at <a href="https://jnode.eu/pageapp/RegModel/RegModel.html?from=github">https://jnode.eu/pageapp/RegModel/RegModel.html</a>
