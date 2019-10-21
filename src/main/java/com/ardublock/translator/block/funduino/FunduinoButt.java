package com.ardublock.translator.block.funduino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class FunduinoButt extends TranslatorBlock
{

	public FunduinoButt(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock Param1Block = this.getRequiredTranslatorBlockAtSocket(0);
		String Param1 = Param1Block.toCode();
		String gename=translator.getBlock(blockId).getGenusName();
		
		String ret = "funduino.butt_";
		if (Param1.startsWith("FUNDUI_BUTT"))
		  Param1 = Param1.substring(11,12);
		if (gename.startsWith("fu_butt_press")) 
		  ret = ret + "pressed(";
		else
		  ret = ret + "released(";
	        ret = ret + Param1 + ")";
		translator.addHeaderFile("Funduino.h");
		translator.addDefinitionCommand("Funduino funduino = Funduino();");
		translator.addSetupCommand("funduino.begin();");
		return ret;
	}

}
