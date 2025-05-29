# Script parameters
param(
    [string]$Path = ".",
    [string]$NewPageName = "blank-page"
)

# Script variables
$HtmlFileName = "$NewPageName.html"
$CssFileName = "$NewPageName.css"
$JsFileName = "$NewPageName.js"
$HtmlFileContent = @"
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>$NewPageName</title>
    <link rel="stylesheet" href="$CssFileName" />
    <script src="$JsFileName" defer></script>
  </head>
  <body>
    
  </body>
</html>
"@

#Get the new page directory and files paths
$NewPageDirPath = Join-Path -Path $Path -ChildPath $NewPageName
$HtmlFilePath = Join-Path -Path $NewPageDirPath -ChildPath $HtmlFileName
$CssFilePath = Join-Path -Path $NewPageDirPath -ChildPath $CssFileName
$JsFilePath = Join-Path -Path $NewPageDirPath -ChildPath $JsFileName

# Create the new page directory and files
New-Item -Path $NewPageDirPath -ItemType Directory | Out-Null
New-Item -Path $HtmlFilePath -ItemType File | Out-Null
New-Item -Path $CssFilePath -ItemType File | Out-Null
New-Item -Path $JsFilePath -ItemType File | Out-Null

# connect the files via HTML
Set-Content -Path $HtmlFilePath -Value $HtmlFileContent