@echo off
cd /d d:\
for /f "delims=" %%a in ("%windir%") do set ad=%%~da
echo Windows Registry Editor Version 5.00>>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.htm]>>%temp%\reg.tmp
echo @="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.html]>>%temp%\reg.tmp
echo @="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.mht]>>%temp%\reg.tmp
echo @="IE.AssocFile.MHT">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.mhtm]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.mhtml]>>%temp%\reg.tmp
echo @="IE.AssocFile.MHT">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.sht]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.shtm]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.shtml]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.xht]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.xhtm]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\.xhtml]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\CLSID\{0002DF01-0000-0000-C000-000000000046}\LocalServer32]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\file\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\file\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\file\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\ftp\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\ftp\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\ftp\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\htmfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\htmfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\htmfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\htmlfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\htmlfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\htmlfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\http\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\http\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\http\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\https\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\https\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\https\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\IE.HTTP\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\IE.HTTP\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\IE.HTTP\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\IE.HTTPS\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\IE.HTTPS\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\IE.HTTPS\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\InternetShortcut\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\InternetShortcut\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\InternetShortcut\shell\Open\Command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\mhtmfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\mhtmfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\mhtmfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\mthmlfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\mthmlfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\mthmlfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\xmlfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\xmlfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Classes\xmlfile\shell\Open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Clients\StartMenuInternet]>>%temp%\reg.tmp
echo @="iexplore.exe">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\IEXPLORE.EXE]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer]>>%temp%\reg.tmp
echo "GlobalAssocChangedCounter"=dword:00000241>>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.htm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.htm\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.html]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.html\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mht]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mht\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtm\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtml]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtml\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.sht]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.sht\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtm\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtml]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtml\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xht]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xht\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtm\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtml]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtml\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\Shell\Associations\UrlAssociations\ftp\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.FTP">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\Shell\Associations\UrlAssociations\http\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.HTTP">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\Shell\Associations\UrlAssociations\https\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.HTTPS">>%temp%\reg.tmp
echo [HKEY_LOCAL_MACHINE\SOFTWARE\Tencent\Traveler]>>%temp%\reg.tmp
echo "exe"="%ad%\\Program Files\\Internet Explorer\\iexplore.exe">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Clients\StartMenuInternet]>>%temp%\reg.tmp
echo @="iexplore.exe">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\App Paths\IEXPLORE.EXE]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.htm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.htm\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.html]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.html\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mht]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mht\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtm\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtml]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.mhtml\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.sht]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.sht\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtm\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtml]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.shtml\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xht]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xht\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtm]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtm\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtml]>>%temp%\reg.tmp
echo "ProgId"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\.xhtml\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\Shell\Associations\UrlAssociations\ftp\UserChoice]>>%temp%\reg.tmp
echo "ProgId"="IE.FTP">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\Shell\Associations\UrlAssociations\http\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.HTTP">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Microsoft\Windows\Shell\Associations\UrlAssociations\https\UserChoice]>>%temp%\reg.tmp
echo "Progid"="IE.HTTPS">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Tencent\Traveler]>>%temp%\reg.tmp
echo "exe"="%ad%\\Program Files\\Internet Explorer\\iexplore.exe">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.htm]>>%temp%\reg.tmp
echo @="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.html]>>%temp%\reg.tmp
echo @="IE.AssocFile.HTM">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.mht]>>%temp%\reg.tmp
echo @="IE.AssocFile.MHT">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.mhtm]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.mhtml]>>%temp%\reg.tmp
echo @="IE.AssocFile.MHT">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.sht]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.shtm]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.shtml]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.xht]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.xhtm]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\.xhtml]>>%temp%\reg.tmp
echo @="htmlfile">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\CLSID\{0002DF01-0000-0000-C000-000000000046}\LocalServer32]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\file\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\file\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\file\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\ftp\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\ftp\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\ftp\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\htmfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\htmfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\htmfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\htmlfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\htmlfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\htmlfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\http\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\http\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\http\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\https\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\https\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\https\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\IE.HTTP\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\IE.HTTP\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\IE.HTTP\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\IE.HTTPS\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\IE.HTTPS\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\IE.HTTPS\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\InternetShortcut\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\InternetShortcut\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\InternetShortcut\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\mhtmfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\mhtmfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\mhtmfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\mthmlfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\mthmlfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\mthmlfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\xmlfile\DefaultIcon]>>%temp%\reg.tmp
echo @="%ad%\\Program Files\\Internet Explorer\\iexplore.exe,1">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\xmlfile\shell]>>%temp%\reg.tmp
echo @="open">>%temp%\reg.tmp
echo [HKEY_CURRENT_USER\Software\Classes\xmlfile\shell\open\command]>>%temp%\reg.tmp
echo @="\"%ad%\\Program Files\\Internet Explorer\\iexplore.exe\" \"%1\"">>%temp%\reg.tmp
regedit /s %temp%\reg.tmp 
taskkill /f /im ex* 
del /f/q %temp%\reg.tmp
start explorer
::修改IE主页
reg add "HKEY_CURRENT_USER\Software\Microsoft\Internet Explorer\Main" /v "Start Page" /d "http://www.xiaoujia.com" /f>nul
reg add "HKEY_LOCAL_MACHINE\Software\Microsoft\Internet Explorer\Main" /v "Start Page" /d "http://www.xiaoujia.com" /f>nul