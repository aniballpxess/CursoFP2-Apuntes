# Script parameters definition
param (
    [string]$Path = ".",
    [string]$Name = "blank-web-project",
    [string]$DirNameHTML = "html",
    [string]$FileNameHTML = "index.html",
    [string]$DirNameCSS = "css",
    [string]$FileNameCSS = "style.css",
    [string]$DirNameJS = "js",
    [string]$FileNameJS = "scripts.js"
)

# Get the project's root directory
$projectRootDir = Join-Path -Path $Path -ChildPath $Name
# Get the file & dir paths for HTML, CSS and JS
$htmlDirPath = Join-Path -Path $projectRootDir -ChildPath $DirNameHTML
$htmlFilePath = Join-Path -Path $htmlDirPath -ChildPath $FileNameHTML
$cssDirPath = Join-Path -Path $projectRootDir -ChildPath $DirNameCSS
$cssFilePath = Join-Path -Path $cssDirPath -ChildPath $FileNameCSS
$jsDirPath = Join-Path -Path $projectRootDir -ChildPath $DirNameJS
$jsFilePath = Join-Path -Path $jsDirPath -ChildPath $FileNameJS
# Set the HTML file content to link the files
$htmlFileContent = @'
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="../css/style.css" />
    <script src="../js/scripts.js"></script>
  </head>
  <body>
    
  </body>
</html>
'@

# Get the original directory
$originalDirectory = Get-Location
# Make project structure
New-Item -Path $htmlDirPath -ItemType Directory -Force
New-Item -Path $htmlFilePath -ItemType File -Force
New-Item -Path $cssDirPath -ItemType Directory -Force
New-Item -Path $cssFilePath -ItemType File -Force
New-Item -Path $jsDirPath -ItemType Directory -Force
New-Item -Path $jsFilePath -ItemType File -Force
# Set HTML file content to link it with the CSS and JS files
Set-Content -Path $htmlFilePath -Value $htmlFileContent -Force
# Return to original directory
Set-Location $originalDirectory