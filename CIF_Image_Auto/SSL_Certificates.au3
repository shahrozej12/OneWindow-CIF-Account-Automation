; AutoIt script to handle certificate dialog
WinWaitActive("Select a certificate") ; Wait for the certificate selection window
Send("{TAB}") ; Move to the first option (if required, adjust this to navigate)
Send("{ENTER}") ; Select the certificate
