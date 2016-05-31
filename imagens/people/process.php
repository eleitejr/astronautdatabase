<?php
include("global.inc.php");

$errors=0;
pt_register('POST','idAstronauta');
pt_register('POST','Primeiro_Nome');
pt_register('POST','Nome_do_Meio');
pt_register('POST','Sobrenome');
pt_register('POST','Data_Nascimento');
pt_register('POST','Cidade_Nascimento');
pt_register('POST','Estado_Nascimento');
pt_register('POST','Pais_Nascimento');
pt_register('POST','Data_Falecimento');

# pt_register('POST','Foto');

pt_register('POST','Sexo');
pt_register('POST','Info');
pt_register('POST','Missao_1');
pt_register('POST','Missao_2');
pt_register('POST','Missao_3');
pt_register('POST','Missao_4');
pt_register('POST','Missao_5');
pt_register('POST','Missao_6');
pt_register('POST','Missao_7');
pt_register('POST','Missao_8');
if($Primeiro_Nome=="" || $Sobrenome=="" || $Data_Nascimento=="" || $Cidade_Nascimento=="" || $Pais_Nascimento=="" || $Sexo=="" ){
$errors=1;
$error.="<li>Voc&ecirc; n&atilde;o informou um ou mais campos requeridos. Por favor, retorne e tente novamente.";
}
if($errors==1) echo $error;
else{
$where_form_is="http".($HTTP_SERVER_VARS["HTTPS"]=="on"?"s":"")."://".$HTTP_SERVER_VARS["SERVER_NAME"].strrev(strstr(strrev($HTTP_SERVER_VARS["PHP_SELF"]),"/"));
$Foto = strtolower($Primeiro_Nome)."_".strtolower($Sobrenome).".jpg";
$message .="idAstronauta: ".$idAstronauta."\n";
$message .="Primeiro Nome: ".$Primeiro_Nome."\n";
$message .="Nome do Meio: ".$Nome_do_Meio."\n";
$message .="Sobrenome: ".$Sobrenome."\n";
$message .="Data Nascimento: ".$Data_Nascimento."\n";
$message .="Cidade Nascimento: ".$Cidade_Nascimento."\n";
$message .="Estado Nascimento: ".$Estado_Nascimento."\n";
$message .="Pais Nascimento: ".$Pais_Nascimento."\n";
$message .="Data Falecimento: ".$Data_Falecimento."\n";
$message .="Foto: ".$Foto."\n";
$message .="Sexo: ".$Sexo."\n";
$message .="Info: ".$Info."\n";
$message .="Missão 1: ".$Missao_1."\n";
$message .="Missão 2: ".$Missao_2."\n";
$message .="Missão 3: ".$Missao_3."\n";
$message .="Missão 4: ".$Missao_4."\n";
$message .="Missão 5: ".$Missao_5."\n";
$message .="Missão 6: ".$Missao_6."\n";
$message .="Missão 7: ".$Missao_7."\n";
$message .="Missão 8: ".$Missao_8."\n";
$message = stripslashes($message);
$headers = 'From:erasmo@astronautdatabase.com';

if(!mail("erasmo@astronautdatabase.com", "Contato do site", $message, $headers))
	 mail("erasmo@astronautdatabase.com", "Contato do site", $message);
$link = mysql_connect("mysql.astronautdatabase.com","astronautdataba","Juliana11@");
mysql_select_db("astronautdatabase",$link);
$query="insert into astronauta (idAstronauta,Primeiro_Nome,Nome_do_Meio,Sobrenome,DtNasc,Cidade_Nasc,Estado_Nasc,Pais_Nasc,DtFalec,Foto,Sexo,Info,m1,m2,m3,m4,m5,m6,m7,m8) values ('".$idAstronauta."','".$Primeiro_Nome."','".$Nome_do_Meio."','".$Sobrenome."','".$Data_Nascimento."','".$Cidade_Nascimento."','".$Estado_Nascimento."','".$Pais_Nascimento."','".$Data_Falecimento."','".$Foto."','".$Sexo."','".$Info."','".$Missao_1."','".$Missao_2."','".$Missao_3."','".$Missao_4."','".$Missao_5."','".$Missao_6."','".$Missao_7."','".$Missao_8."')";
mysql_query($query);
$make=fopen("admin/data.dat","a");
$to_put="";
$to_put .= $idAstronauta."|".$Primeiro_Nome."|".$Nome_do_Meio."|".$Sobrenome."|".$Data_Nascimento."|".$Cidade_Nascimento."|".$Estado_Nascimento."|".$Pais_Nascimento."|".$Data_Falecimento."|".$Foto."|".$Sexo."|".$Info."|".$Missao_1."|".$Missao_2."|".$Missao_3."|".$Missao_4."|".$Missao_5."|".$Missao_6."|".$Missao_7."|".$Missao_8."
";
fwrite($make,$to_put);
?>


<!-- Este &eacute; o conte&uacute;do da sua p&aacute;gina de agradecimento, seja cuidadoso ao modific&aacute;-lo -->

<table width=50% align='center'>
<tr><td><h2>Obrigado!</h2></td></tr>
<tr><td>Os dados foram enviados com sucesso.</td></tr>
<tr><td>idAstronauta: </td><td> <?php echo $idAstronauta; ?> </td></tr>
<tr><td>Primeiro Nome: </td><td> <?php echo $Primeiro_Nome; ?> </td></tr>
<tr><td>Nome do Meio: </td><td> <?php echo $Nome_do_Meio; ?> </td></tr>
<tr><td>Sobrenome: </td><td> <?php echo $Sobrenome; ?> </td></tr>
<tr><td>Data Nascimento: </td><td> <?php echo $Data_Nascimento; ?> </td></tr>
<tr><td>Cidade Nascimento: </td><td> <?php echo $Cidade_Nascimento; ?> </td></tr>
<tr><td>Estado Nascimento: </td><td> <?php echo $Estado_Nascimento; ?> </td></tr>
<tr><td>Pais Nascimento: </td><td> <?php echo $Pais_Nascimento; ?> </td></tr>
<tr><td>Data Falecimento: </td><td> <?php echo $Data_Falecimento; ?> </td></tr>
<tr><td>Foto: </td><td> <?php echo $Foto; ?> </td></tr>
<tr><td>Sexo: </td><td> <?php echo $Sexo; ?> </td></tr>
<tr><td>Info: </td><td> <?php echo $Info; ?> </td></tr>
<tr><td>Missão 1: </td><td> <?php echo $Missao_1; ?> </td></tr>
<tr><td>Missão 2: </td><td> <?php echo $Missao_2; ?> </td></tr>
<tr><td>Missão 3: </td><td> <?php echo $Missao_3; ?> </td></tr>
<tr><td>Missão 4: </td><td> <?php echo $Missao_4; ?> </td></tr>
<tr><td>Missão 5: </td><td> <?php echo $Missao_5; ?> </td></tr>
<tr><td>Missão 6: </td><td> <?php echo $Missao_6; ?> </td></tr>
<tr><td>Missão 7: </td><td> <?php echo $Missao_7; ?> </td></tr>
<tr><td>Missão 8: </td><td> <?php echo $Missao_8; ?> </td></tr>
</table>
<br><a href="../../../index.php"><img src="../../../imagens/back_button-128.png"  align="center"></img></a>
<!-- Do not change anything below this line -->

<?php 
}
?>
