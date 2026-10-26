package nischitweaker.jei.qualitytools;

import com.tmtravlr.qualitytools.config.QualityEntry;
import com.tmtravlr.qualitytools.config.QualityType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;

import java.text.DecimalFormat;
import java.util.*;

//Colored quality names, flowing in centered lines. Hovering a name shows its reforging chance and attributes
public class QTQualityLabels {
    private static final int LINE_HEIGHT = 10;
    private static final int GAP = 6;
    private static final DecimalFormat CHANCE_FORMAT = new DecimalFormat("#.#");
    //Luck potion icon, drawn at half size
    private static final int LUCK_ICON_SIZE = 9;

    //Many gear items share a quality type, so layouts are shared too
    private static final Map<QualityType, QTQualityLabels> cache = new HashMap<>();

    private final QualityType qualityType;
    private final List<Label> labels = new ArrayList<>();
    private int height = 0;
    private String languageCode = null;

    private QTQualityLabels(QualityType qualityType) {
        this.qualityType = qualityType;
    }

    public static QTQualityLabels get(QualityType qualityType) {
        return cache.computeIfAbsent(qualityType, QTQualityLabels::new);
    }

    public int getHeight() {
        updateLayout();
        return height;
    }

    public void draw(Minecraft mc, int top) {
        updateLayout();
        for (Label label : labels)
            mc.fontRenderer.drawString(label.text, label.x, top + label.y, 0xFFFFFF);

        boolean boundIconTexture = false;
        for (Label label : labels) {
            if (!label.luckAffected) continue;
            if (!boundIconTexture) {
                mc.getTextureManager().bindTexture(InventoryEffectRenderer.INVENTORY_BACKGROUND);
                GlStateManager.color(1, 1, 1, 1);
                GlStateManager.enableBlend();
                boundIconTexture = true;
            }
            int iconIndex = MobEffects.LUCK.getStatusIconIndex();
            GlStateManager.pushMatrix();
            GlStateManager.translate(label.x + label.width - LUCK_ICON_SIZE, top + label.y - 1, 0);
            GlStateManager.scale(0.5, 0.5, 1);
            Gui.drawModalRectWithCustomSizedTexture(0, 0, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18, 256, 256);
            GlStateManager.popMatrix();
        }
    }

    public List<String> getTooltip(int top, int mouseX, int mouseY) {
        updateLayout();
        for (Label label : labels)
            if (mouseX >= label.x && mouseX < label.x + label.width && mouseY >= top + label.y - 1 && mouseY < top + label.y + LINE_HEIGHT - 1)
                return getAttributeTooltip(label);
        return Collections.emptyList();
    }

    //Recomputed if the language changes, since names and widths change with it
    private void updateLayout() {
        String currentLanguage = Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();
        if (currentLanguage.equals(languageCode)) return;
        languageCode = currentLanguage;
        labels.clear();

        //Reforging skips the "normal" quality, so chances are relative to all other entries
        int totalWeight = 0;
        for (QualityEntry entry : qualityType.qualities)
            if (!isNormal(entry)) totalWeight += entry.weight;

        boolean luckifiedLoaded = Loader.isModLoaded("luckified");
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        List<List<Label>> lines = new ArrayList<>();
        List<Label> currentLine = new ArrayList<>();
        int lineWidth = 0;
        for (QualityEntry entry : qualityType.qualities) {
            if (isNormal(entry)) continue;
            String chance = CHANCE_FORMAT.format(100.0 * entry.weight / totalWeight) + "%";
            String text = entry.color + I18n.format(entry.name);
            boolean luckAffected = luckifiedLoaded && LuckifiedCompat.isLuckAffected(entry.name);
            Label label = new Label(entry, text, chance, font.getStringWidth(text) + (luckAffected ? LUCK_ICON_SIZE + 1 : 0), luckAffected);

            int widthWithLabel = lineWidth + (currentLine.isEmpty() ? 0 : GAP) + label.width;
            if (!currentLine.isEmpty() && widthWithLabel > QTRecipeCategory.WIDTH) {
                lines.add(currentLine);
                currentLine = new ArrayList<>();
                widthWithLabel = label.width;
            }
            currentLine.add(label);
            lineWidth = widthWithLabel;
        }
        if (!currentLine.isEmpty()) lines.add(currentLine);

        for (int i = 0; i < lines.size(); i++) {
            List<Label> line = lines.get(i);
            int width = line.stream().mapToInt(l -> l.width).sum() + GAP * (line.size() - 1);
            int x = (QTRecipeCategory.WIDTH - width) / 2;
            for (Label label : line) {
                label.x = x;
                label.y = i * LINE_HEIGHT;
                x += label.width + GAP;
                labels.add(label);
            }
        }
        height = lines.size() * LINE_HEIGHT;
    }

    //Same format as the Quality Tools item tooltip
    private List<String> getAttributeTooltip(Label label) {
        QualityEntry entry = label.entry;
        List<String> tooltip = new ArrayList<>();
        tooltip.add(entry.color + I18n.format(entry.name));
        tooltip.add(TextFormatting.GRAY + I18n.format("nischitweaker.jei.qualitytools.chance", label.chance));
        if (label.luckAffected)
            tooltip.add(TextFormatting.GREEN + I18n.format("nischitweaker.jei.qualitytools.luck"));
        for (String slot : qualityType.slots)
            tooltip.add(TextFormatting.GRAY + I18n.format("item.modifiers." + slot.toLowerCase()));

        for (Map.Entry<String, AttributeModifier> attribute : entry.attributeMap.entries()) {
            AttributeModifier modifier = attribute.getValue();
            double amount = modifier.getAmount();
            double displayAmount = modifier.getOperation() == 1 || modifier.getOperation() == 2 ? amount * 100 : amount;
            String attributeName = I18n.format("attribute.name." + attribute.getKey());
            if (amount > 0)
                tooltip.add(TextFormatting.BLUE + " " + I18n.format("attribute.modifier.plus." + modifier.getOperation(), ItemStack.DECIMALFORMAT.format(displayAmount), attributeName));
            else if (amount < 0)
                tooltip.add(TextFormatting.RED + " " + I18n.format("attribute.modifier.take." + modifier.getOperation(), ItemStack.DECIMALFORMAT.format(-displayAmount), attributeName));
        }
        return tooltip;
    }

    private static boolean isNormal(QualityEntry entry) {
        return entry == null || entry.name.equalsIgnoreCase("normal");
    }

    private static class Label {
        private final QualityEntry entry;
        private final String text;
        private final String chance;
        private final int width;
        private final boolean luckAffected;
        private int x;
        private int y;

        private Label(QualityEntry entry, String text, String chance, int width, boolean luckAffected) {
            this.entry = entry;
            this.text = text;
            this.chance = chance;
            this.width = width;
            this.luckAffected = luckAffected;
        }
    }
}
