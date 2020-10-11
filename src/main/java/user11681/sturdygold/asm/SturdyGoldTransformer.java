package user11681.sturdygold.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import user11681.fabricasmtools.TransformerPlugin;

public class SturdyGoldTransformer extends TransformerPlugin {
    @Override
    public void onLoad(final String mixinPackage) {
        super.onLoad(mixinPackage);

        this.registerPostMixinTransformer(klass(1740), SturdyGoldTransformer::transformArmorMaterials);
        this.registerPostMixinTransformer(klass(1834), SturdyGoldTransformer::transformToolMaterials);
    }

    private static void transformArmorMaterials(final String targetClassName, final ClassNode targetClass, final String mixinClassName, final IMixinInfo info) {
        for (final MethodNode method : targetClass.methods) {
            if (method.name.equals("<clinit>")) {
                final InsnList instructions = method.instructions;

                AbstractInsnNode instruction = instructions.getFirst();

                while (instruction.getOpcode() != Opcodes.LDC || !"gold".equals(((LdcInsnNode) instruction).cst)) {
                    instruction = instruction.getNext();
                }

                while (instruction.getOpcode() != Opcodes.BIPUSH || ((IntInsnNode) instruction).operand != 7) {
                    instruction = instruction.getNext();
                }

                ((IntInsnNode) instruction).operand = 24;

                while (instruction.getOpcode() != Opcodes.ICONST_1) {
                    instruction = instruction.getNext();
                }

                instructions.set(instruction, instruction = new InsnNode(Opcodes.ICONST_3));

                instruction = instruction.getNext();

                while (instruction.getOpcode() != Opcodes.ICONST_3) {
                    instruction = instruction.getNext();
                }

                instructions.set(instruction, instruction = new InsnNode(Opcodes.ICONST_5));

                instruction = instruction.getNext();

                while (instruction.getOpcode() != Opcodes.ICONST_5) {
                    instruction = instruction.getNext();
                }

                instructions.set(instruction, instruction = new IntInsnNode(Opcodes.BIPUSH, 6));

                instruction = instruction.getNext();

                while (instruction.getOpcode() != Opcodes.ICONST_2) {
                    instruction = instruction.getNext();
                }

                instructions.set(instruction, instruction = new InsnNode(Opcodes.ICONST_3));
            }
        }
    }

    private static void transformToolMaterials(final String targetClassName, final ClassNode targetClass, final String mixinClassName, final IMixinInfo info) {
        for (final MethodNode method : targetClass.methods) {
            if (method.name.equals("<clinit>")) {
                final InsnList instructions = method.instructions;

                AbstractInsnNode instruction = instructions.getFirst();

                while (instruction.getOpcode() != Opcodes.LDC || !"GOLD".equals(((LdcInsnNode) instruction).cst)) {
                    instruction = instruction.getNext();
                }

                while (instruction.getOpcode() != Opcodes.ICONST_0) {
                    instruction = instruction.getNext();
                }

                instructions.set(instruction, instruction = new InsnNode(Opcodes.ICONST_2));

                while (instruction.getOpcode() != Opcodes.BIPUSH || ((IntInsnNode) instruction).operand != 32) {
                    instruction = instruction.getNext();
                }

                final IntInsnNode durability = (IntInsnNode) instruction;

                durability.setOpcode(Opcodes.SIPUSH);
                durability.operand = 768;

                while (instruction.getOpcode() != Opcodes.FCONST_0) {
                    instruction = instruction.getNext();
                }

                instructions.set(instruction, new LdcInsnNode(2F));

                return;
            }
        }
    }
}
