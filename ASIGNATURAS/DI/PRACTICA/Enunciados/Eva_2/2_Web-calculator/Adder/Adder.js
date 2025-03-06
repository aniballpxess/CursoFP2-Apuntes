function F_Events_handle(P_S_Event)
{
	G_S_Event=P_S_Event;
	F_New_state_evolution_to();
	F_New_state_establishment();
}
function F_New_state_evolution_to()
{
	switch(G_S_State)
	{
		case "A. A1Init.":
			switch(G_S_Event)
			{
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					G_S_State="B. A1Intr.";
					break;
				case "Reset":
					G_S_State="A. A1Init.";
			}
			break;
		case "B. A1Intr.":
			switch(G_S_Event)
			{
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					G_S_State="B. A1Intr.";
					break;
				case "Plus":
					G_S_State="C. A2Init.";					
					break;
				case "Reset":
					G_S_State="A. A1Init.";
			}
			break;
		case "C. A2Init.":
			switch(G_S_Event)
			{
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					G_S_State="D. A2Intr.";
					break;
				case "Reset":
					G_S_State="A. A1Init.";
			}
			break;
		case "D. A2Intr.":
			switch(G_S_Event)
			{
				case "0":
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					G_S_State="D. A2Intr.";
					break;
				case "Equal":
					G_S_State="E. RS";
					break;
				case "Reset":
					G_S_State="A. A1Init.";
			}
			break;
		case "E. RS":
			switch(G_S_Event)
			{
				case "Reset":
					G_S_State="A. A1Init.";
			}
	}
}
function F_New_state_establishment()
{
	switch(G_S_State)
	{
		case "A. A1Init.":
			G_S_Addend_1="";
			F_Display(G_S_Addend_1);
			F_Number_buttons_enabling(true);
			F_Plus_operation_button_enabling(false);
			F_Equal_operation_button_enabling(false);
			break;
		case "B. A1Intr.":
			G_S_Addend_1=G_S_Addend_1.concat(G_S_Event);
			F_Display(G_S_Addend_1);
			F_Number_buttons_enabling(true);
			F_Plus_operation_button_enabling(true);
			F_Equal_operation_button_enabling(false);
			break;
		case "C. A2Init.":			
			G_S_Addend_2="";
			F_Display(G_S_Addend_2);
			F_Number_buttons_enabling(true);
			F_Plus_operation_button_enabling(false);
			F_Equal_operation_button_enabling(false);
			break;
		case "D. A2Intr.":
			G_S_Addend_2=G_S_Addend_2.concat(G_S_Event);
			F_Display(G_S_Addend_2);
			F_Number_buttons_enabling(true);
			F_Plus_operation_button_enabling(false);
			F_Equal_operation_button_enabling(true);
			break;
		case "E. RS":
			F_Addition();
			F_Display(G_S_Result);
			F_Number_buttons_enabling(false);
			F_Plus_operation_button_enabling(false);
			F_Equal_operation_button_enabling(false);
	}
}
function F_Display(P_S_Text)
{
	var L_E_Display;
	L_E_Display=document.getElementById("I_Display");
	L_E_Display.value=P_S_Text;
}
function F_Number_buttons_enabling(P_B_Enabled)
{
	var L_E_Button;
	L_E_Button=document.getElementById("I_0_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_1_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_2_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_3_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_4_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_5_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_6_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_7_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_8_number_button");
	L_E_Button.disabled=!P_B_Enabled;
	L_E_Button=document.getElementById("I_9_number_button");
	L_E_Button.disabled=!P_B_Enabled;
}
function F_Plus_operation_button_enabling(P_B_Enabled)
{
	var L_E_Button;
	L_E_Button=document.getElementById("I_Plus_operation_button");
	L_E_Button.disabled=!P_B_Enabled;

}
function F_Equal_operation_button_enabling(P_B_Enabled)
{
	var L_E_Button;
	L_E_Button=document.getElementById("I_Equal_operation_button");
	L_E_Button.disabled=!P_B_Enabled;
}