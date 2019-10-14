package com.ardublock.translator.block.funduino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class FunduinoLED extends TranslatorBlock
{

	public FunduinoLED(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock Param1Block = this.getRequiredTranslatorBlockAtSocket(0),
		                Param2Block = this.getTranslatorBlockAtSocket(1);
		String Param1 = Param1Block.toCode();
		
		String ret = "\tfunduino.led(" + Param1;
		if (Param2Block != null) 
		  ret = ret + "," + Param2Block.toCode();
		ret = ret + ");\n";
		translator.addHeaderFile("Funduino.h");
		translator.addDefinitionCommand("Funduino funduino = Funduino();");
		translator.addSetupCommand("funduino.begin();");
		return ret;
	}

}
