# --- Script parameters ---
param (
    [string]$Path = ".",
    [string]$ProjectName,
    [string]$FirstPageName,
    [string]$SourceDirName = "src",
    [string]$ResourcesDirName = "res"
)

# --- Script variables ---
$DefaultProjectName = "blank-project"
$DefaultFirstPageName = "blank-page"

# --- Script body ---
# Ask user for the project's & the first page's names
if (-not $ProjectName) {
  $ProjectName = Read-Host "Nombre del proyecto (por defecto: $DefaultProjectName)"
}
if (-not $FirstPageName) {
  $FirstPageName = Read-Host "Nombre de la primera pagina (por defecto: $DefaultFirstPageName)"
}

# Stablish default names if needed
if (-not $ProjectName) {
  $ProjectName = $DefaultProjectName
}
if (-not $FirstPageName) {
  $FirstPageName = $DefaultFirstPageName
}

# Get the project's root, src & res paths
$RootDirPath = Join-Path -Path $Path -ChildPath $ProjectName
$SourceDirPath = Join-Path -Path $RootDirPath -ChildPath $SourceDirName
$ResourcesDirPath = Join-Path -Path $RootDirPath -ChildPath $ResourcesDirName

# Make project structure
New-Item -Path $RootDirPath -ItemType Directory | Out-Null
New-Item -Path $ResourcesDirPath -ItemType Directory | Out-Null
New-Item -Path $SourceDirPath -ItemType Directory | Out-Null
Write-Host "Estructura del proyecto creada en: $RootDirPath"

# Create the first page
& .\new-page.ps1 -Path $SourceDirPath -NewPageName $FirstPageName
Write-Host "Pagina con nombre $FirstPageName creada"