package frame_transition;

import android.content.Context;
import android.content.Intent;

public class ActivityTransition {
    Context currCtx;
    public ActivityTransition(Context ctx)
    {
        this.currCtx=ctx;
    }

    public void moveTo(Class next)
    {
        Intent intent = new Intent(currCtx,next);
        currCtx.startActivity(intent);
    }

}
