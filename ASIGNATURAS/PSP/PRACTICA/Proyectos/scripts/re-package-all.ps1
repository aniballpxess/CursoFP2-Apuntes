$projectRoot = "C:\CursoFP2\ASIGNATURAS\PSP\PRACTICA\Proyectos\materiales"
$targetDirectory = ".\bin\psp"
$oldPackagedFiles = ".\bin\psp\*.jar"
$newPackagedFiles = ".\target\*.jar"

# Get the original directory
$originalDirectory = Get-Location
# Package all Java exercises through Maven
Set-Location $projectRoot
mvn package -P aleatorios1
mvn package -P aleatorios2
mvn package -P divisores
mvn package -P doble1
mvn package -P doble2
mvn package -P europa1
mvn package -P europa2
mvn package -P extremos1
mvn package -P extremos2
mvn package -P mayusculas1
mvn package -P mayusculas2
mvn package -P media1 
mvn package -P media2
mvn package -P sumatorio
# Check target directory existence
$targetDirectoryDoesNotExist = !(Test-Path -PathType Container $targetDirectory)
if ($targetDirectoryDoesNotExist) {
    New-Item -ItemType Directory -Path $targetDirectory
}
# Export all packaged files
Remove-Item -Path $oldPackagedFiles
Move-Item -Path $newPackagedFiles -Destination $targetDirectory
# Return to original directory
Set-Location $originalDirectory