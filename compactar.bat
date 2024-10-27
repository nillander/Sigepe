@echo off
setlocal

:: Define o nome do arquivo ZIP a ser criado
set "zip_file=Sigepe.zip"

:: Remove o arquivo ZIP se ele já existir
if exist "%zip_file%" del "%zip_file%"

:: Compacta todos os arquivos e pastas, exceto o arquivo batch, as pastas target e .git, e o próprio arquivo ZIP
tar -cf "%zip_file%" --exclude=target --exclude=.git --exclude=%~nx0 --exclude=%zip_file% *

echo Arquivo compactado com sucesso em "%zip_file%"
endlocal
