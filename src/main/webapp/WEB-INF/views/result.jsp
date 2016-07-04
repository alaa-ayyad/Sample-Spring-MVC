<#import "/spring.ftl" as spring/>
 
<@customize.page title="Form Submit Result">

	<h3>
	Thanks ${form.name} for submitting your message!
	</h3>
	
	<div>
	 The message:<br>
	 <span style="background: lime;">${form.description}</span>
	</div>
  
</@customize.page>
