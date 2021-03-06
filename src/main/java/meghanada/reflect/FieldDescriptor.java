package meghanada.reflect;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nullable;
import meghanada.utils.ClassNameUtils;

public class FieldDescriptor extends MemberDescriptor {

  private static final long serialVersionUID = 7817892454168748759L;

  public FieldDescriptor(
      final String declaringClass,
      final String name,
      @Nullable final String modifier,
      final String returnType) {
    this.declaringClass = declaringClass;
    this.name = name.trim();
    this.memberType = MemberType.FIELD;
    if (isNull(modifier)) {
      this.modifier = "";
    } else {
      this.modifier = modifier.trim();
    }
    this.returnType = returnType;
    this.typeParameterMap = new HashMap<>(4);
  }

  public static CandidateUnit createVar(String declaringClass, String name, String returnType) {
    FieldDescriptor descriptor = new FieldDescriptor(declaringClass, name, "", returnType);
    descriptor.memberType = MemberType.VAR;
    return descriptor;
  }

  @Override
  public String getDeclaration() {
    StringBuilder sb = new StringBuilder(32);
    if (nonNull(this.modifier) && !this.modifier.isEmpty()) {
      sb.append(this.modifier).append(' ');
    }
    return sb.append(this.getDisplayDeclaration()).toString();
  }

  @Override
  public String getDisplayDeclaration() {
    final String returnType = this.getReturnType();
    if (isNull(returnType)) {
      return "";
    }
    final String rt = ClassNameUtils.getSimpleName(returnType) + ' ' + this.name;
    return ClassNameUtils.replaceInnerMark(rt);
  }

  @Nullable
  @Override
  public String getReturnType() {
    if (nonNull(this.returnType)) {
      String rt = this.returnType;
      if (this.hasTypeParameters()) {
        rt = super.renderTypeParameters(rt, false);
      }
      return rt;
    }
    return null;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("declaringClass", declaringClass)
        .add("name", name)
        .add("returnType", returnType)
        .add("info", getDeclaration())
        .toString();
  }

  @Override
  public List<String> getParameters() {
    return Collections.emptyList();
  }

  @Nullable
  @Override
  public String getRawReturnType() {
    if (nonNull(this.returnType)) {
      final String rt = this.returnType;
      if (this.hasTypeParameters()) {
        return super.renderTypeParameters(rt, false);
      }
      return rt;
    }
    return null;
  }

  @Override
  public String getSig() {
    return ClassNameUtils.removeTypeParameter(this.returnType);
  }
}
