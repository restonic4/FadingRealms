package me.restonic4.fading_realms.util.Camera.Cutscene;

import me.restonic4.restapi.RestApi;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class EasingTransitionParser {

    public static EasingTransition parseEasingTransition(String input) {
        String[] parts = input.replaceAll("\\s+", "").split(",");

        Vec3 startVec = new Vec3(
                Double.parseDouble(parts[0].replace("newEasingTransition(newVec3(", "")),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2].replace(")", ""))
        );

        Vec3 endVec = new Vec3(
                Double.parseDouble(parts[3].replace("newVec3(", "")),
                Double.parseDouble(parts[4]),
                Double.parseDouble(parts[5].replace(")", ""))
        );

        Vec2 startRot = new Vec2(
                Float.parseFloat(parts[6].replace("newVec2(", "")),
                Float.parseFloat(parts[7].replace(")", ""))
        );

        Vec2 endRot = new Vec2(
                Float.parseFloat(parts[8].replace("newVec2(", "")),
                Float.parseFloat(parts[9].replace(")", ""))
        );

        double startFov = Double.parseDouble(parts[10]);
        double endFov = Double.parseDouble(parts[11]);

        int duration = Integer.parseInt(parts[12]);

        String easingName = parts[13].replace("newEasing().", "");
        easingName = easingName.replace("()", "");
        easingName = easingName.replace(")", "");

        Vec3 bezierPoint1 = null;
        Vec3 bezierPoint2 = null;

        if (easingName.contains("setBezier")) {
            String joined = "";

            for (String part : parts) {
                joined = joined + part + ",";
            }

            String easingPart = easingName.split("setBezier")[0];
            String bezierPart = joined.split("setBezier")[1];

            easingName = easingPart.replace(".", "");
            bezierPart = bezierPart.replace("(newVec3(", "").replace("newVec3(", "");

            RestApi.Log(bezierPart);

            double x = Double.parseDouble(bezierPart.split(",")[0]);
            double y = Double.parseDouble(bezierPart.split(",")[1]);
            double z = Double.parseDouble(bezierPart.split(",")[2].replace(")", ""));

            bezierPoint1 = new Vec3(x, y, z);

            if (!bezierPart.contains("null")) {
                double x2 = Double.parseDouble(bezierPart.split(",")[3]);
                double y2 = Double.parseDouble(bezierPart.split(",")[4]);
                double z2 = Double.parseDouble(bezierPart.split(",")[5].replace("))", ""));

                bezierPoint2 = new Vec3(x2, y2, z2);
            }
        }

        Easing easing = new Easing(easingName);

        return new EasingTransition(startVec, endVec, startRot, endRot, startFov, endFov, duration, easing).setBezier(bezierPoint1, bezierPoint2);
    }
}
