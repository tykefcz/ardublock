package com.ardublock.translator.block.funduino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class FunduinoDisplay extends TranslatorBlock
{

	public FunduinoDisplay(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock Param1Block = this.getRequiredTranslatorBlockAtSocket(0);
		String Param1 = Param1Block.toCode();
		String ret = "\tfunduino.display(" + Param1 + ");\n";
		translator.addHeaderFile("Funduino.h");
		translator.addDefinitionCommand("Funduino funduino = Funduino();");
		translator.addSetupCommand("funduino.begin();");
		return ret;
	}

}
