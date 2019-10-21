package com.ardublock.translator.block.funduino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class FunduinoTimer extends TranslatorBlock
{

	public FunduinoTimer(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String gename=translator.getBlock(blockId).getGenusName();
		String ret = "";
		if (gename.startsWith("fu_is_timer")) { // bool(num)
		  ret = "funduino.is_timer(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ")";
		} else if (gename.startsWith("fu_timer_rep_start")) { // proc(num,num);
		  ret = "funduino.timer_start(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ","
		      + this.getRequiredTranslatorBlockAtSocket(1).toCode() + ");";
		} else if (gename.startsWith("fu_timer_once_start")) { // proc(num,num);
		  ret = "funduino.timer_start(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ","
		      + this.getRequiredTranslatorBlockAtSocket(1).toCode() + ",false);";
		} else if (gename.startsWith("fu_timer_stop")) { // proc(num);
		  ret = "funduino.timer_stop(" 
		      + this.getRequiredTranslatorBlockAtSocket(0).toCode() + ");";
		}
		translator.addHeaderFile("Funduino.h");
		translator.addDefinitionCommand("Funduino funduino = Funduino();");
		translator.addSetupCommand("funduino.begin();");
		return ret;
	}

}
