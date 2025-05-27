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

# Get the original directory
$originalDirectory = Get-Location
Write-Host "Original dir saved"

# Make project structure
New-Item -Path $RootDirPath -ItemType Directory
New-Item -Path $ResourcesDirPath -ItemType Directory
New-Item -Path $SourceDirPath -ItemType Directory
Write-Host "Basic project structure created"

# Create the first page
& .\new-page.ps1 -Path $SourceDirPath -NewPageName $FirstPageName
Write-Host "First page created"

# Return to original directory
Set-Location $originalDirectory
Write-Host "Returned to original directory"