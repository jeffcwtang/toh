#Deploy Angular website on windows IIS container
FROM microsoft/iis:10.0.14393.206
SHELL ["powershell"]

WORKDIR /install

#install URL rewrite which required by Angular web.config
COPY resources\\rewrite_amd64_en-US.msi .

RUN Write-Host 'Installing URL Rewrite' ; \
    Start-Process msiexec.exe -ArgumentList '/i', 'rewrite_amd64_en-US.msi', '/quiet', '/norestart' -NoNewWindow -Wait

COPY .\\dist C:\\inetpub\\wwwroot

RUN Remove-WebSite -Name 'Default Web Site'
RUN New-Website -Name 'test site' -Port 80 -PhysicalPath 'C:\inetpub\wwwroot'

EXPOSE 80

CMD Write-Host IIS Started... ; \
    while ($true) { Start-Sleep -Seconds 3600 }